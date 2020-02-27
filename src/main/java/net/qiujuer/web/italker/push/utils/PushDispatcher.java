package net.qiujuer.web.italker.push.utils;

import com.gexin.rp.sdk.base.IBatch;
import com.gexin.rp.sdk.base.IPushResult;
import com.gexin.rp.sdk.base.IQueryResult;
import com.gexin.rp.sdk.base.impl.SingleMessage;
import com.gexin.rp.sdk.base.impl.Target;
import com.gexin.rp.sdk.http.IGtPush;
import com.gexin.rp.sdk.template.NotificationTemplate;
import com.gexin.rp.sdk.template.TransmissionTemplate;
import com.gexin.rp.sdk.template.style.Style0;
import com.google.common.base.Strings;
import net.qiujuer.web.italker.push.bean.api.base.PushModel;
import net.qiujuer.web.italker.push.bean.db.User;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class PushDispatcher {
    private static final String appId = "lpVqsklbR09q9AaxM7sKA8";
    private static final String appKey = "e21KGyw5ET8M2bZz5Yun";
    private static final String masterSecret = "UBullLxoHl9vUQwkcpJo58";
    private static final String host = "http://sdk.open.api.igexin.com/apiex.htm";

    private final IGtPush pusher;
    // 要收到消息的人和内容的列表
    private final List<BatchBean> beans = new ArrayList<> ();

    public PushDispatcher() {
        // 最根本的发送者
        pusher = new IGtPush(host, appKey, masterSecret);
    }

    /**
     * 添加一条消息
     *
     * @param receiver 接收者
     * @param model    接收的推送Model
     * @return 是否添加成功
     */
    public boolean add(User receiver, PushModel model) {
        // 基础检查，必须有接收者的设备的Id
        if (receiver == null || model == null ||
                Strings.isNullOrEmpty(receiver.getPushId()))
            return false;

        String pushString = model.getPushString();
        if (Strings.isNullOrEmpty(pushString))
            return false;


        // 构建一个目标+内容
        BatchBean bean = buildMessage(receiver.getPushId(), pushString);
        beans.add(bean);

        //判断当前是否在线，不在线推送一个通知栏消息
        if(!isUserOnline(receiver.getPushId())){
            BatchBean batchBean = buildNotification(receiver.getPushId(), pushString);
            beans.add(batchBean);
        }
        return true;
    }

    /**
     * 对要发送的数据进行格式化封装
     *
     * @param clientId 接收者的设备Id
     * @param text     要接收的数据
     * @return BatchBean
     */
    private BatchBean buildMessage(String clientId, String text) {
        // 透传消息，不是通知栏显示，而是在MessageReceiver收到
        TransmissionTemplate template = new TransmissionTemplate();
        template.setAppId(appId);
        template.setAppkey(appKey);
        template.setTransmissionContent(text);
        template.setTransmissionType(0); //这个Type为int型，填写1则自动启动app

        SingleMessage message = new SingleMessage();
        message.setData(template); // 把透传消息设置到单消息模版中
        message.setOffline(true); // 是否运行离线发送
        message.setOfflineExpireTime(24 * 3600 * 1000); // 离线消息时常

        // 设置推送目标，填入appid和clientId
        Target target = new Target();
        target.setAppId(appId);
        target.setClientId(clientId);
        // 返回一个封装
        return new BatchBean(message, target);
    }

    /**
     * 构建一个统治栏消息
     *
     * @param clientId 接收者的设备Id
     * @param text     要接收的数据
     * @return BatchBean
     */
    private BatchBean buildNotification(String clientId, String text) {

        NotificationTemplate notificationTemplate = notificationTemplateDemo();

        SingleMessage message = new SingleMessage();
        message.setData(notificationTemplate);

        // 设置推送目标，填入appid和clientId
        Target target = new Target();
        target.setAppId(appId);
        target.setClientId(clientId);
        // 返回一个封装
        return new BatchBean(message, target);
    }

    // 进行消息最终发送
    public boolean submit() {
        // 构建打包的工具类
        IBatch batch = pusher.getBatch();
        // 是否有数据需要发送
        boolean haveData = false;

        for (BatchBean bean : beans) {
            try {
                batch.add(bean.message, bean.target);
                haveData = true;
            } catch (Exception e) {
                e.printStackTrace();

            }
        }

        // 没有数据就直接返回
        if (!haveData)
            return false;

        IPushResult result = null;
        try {
            result = batch.submit();
        } catch (IOException e) {
            e.printStackTrace();

            // 失败情况下尝试重复发送一次
            try {
                batch.retry();
            } catch (IOException e1) {
                e1.printStackTrace();
            }

        }

        if (result != null) {
            try {
                Logger.getLogger("PushDispatcher")
                        .log(Level.INFO, (String) result.getResponse().get("result"));
                return true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        Logger.getLogger("PushDispatcher")
                .log(Level.WARNING, "推送服务器响应异常！！！");
        return false;

    }

    // 给每个人发送消息的一个Bean封装
    private static class BatchBean {
        SingleMessage message;
        Target target;

        BatchBean(SingleMessage message, Target target) {
            this.message = message;
            this.target = target;
        }
    }

    /**
     * 获取用户在线状态
     * @param clientId 客户端身份ID
     * @return 用户状态
     */
    private Boolean  isUserOnline(String clientId){
        String status=(String)pusher.getClientIdStatus(appId,clientId).getResponse().get("result");
        if(status=="online"){
            return true;
        }else {
            return false;
        }
    }
    public static NotificationTemplate notificationTemplateDemo() {
        NotificationTemplate template = new NotificationTemplate();
        template.setAppId(appId);
        template.setAppkey(appId);
        template.setTransmissionType(1);
        template.setTransmissionContent("请输入您要透传的内容");

        Style0 style = new Style0();
        // 设置通知栏标题与内容
        style.setTitle("请输入通知栏标题");
        style.setText("请输入通知栏内容");
        // 配置通知栏图标
        style.setLogo("icon.png");
        // 配置通知栏网络图标
        style.setLogoUrl("");
        // 设置通知是否响铃，震动，或者可清除
        style.setRing(true);
        style.setVibrate(true);
        style.setClearable(true);
//        style.setChannel("自定义channel");
//        style.setChannelName("自定义channelName");
//        style.setChannelLevel(3);
        template.setStyle(style);
        return template;
    }
}
