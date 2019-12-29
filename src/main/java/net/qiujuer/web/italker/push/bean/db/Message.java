package net.qiujuer.web.italker.push.bean.db;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="Message")
public class Message {
    public static final int TYPE_STR=1;//字符串类型
    public static final int TYPE_PIC=2;//图片类型
    public static final int TYPE_FILE=3;//文件类型
    public static final int TYPE_AUDIO = 4; // 语音类型
    @Id
    @PrimaryKeyJoinColumn
    //主键生成存储的类型为UUID
    //@GeneratedValue(generator = "uuid")
    @GenericGenerator(name="uuid",strategy = "uuid2")
    @Column(updatable = false,nullable = false)
    private String id;

    //消息内容
    @Column(nullable = false,columnDefinition = "TEXT")
    private String content;

    //附件
    @Column()
    private String attach;

    //消息类型
    @Column(nullable = false)
    private int type;

    //创建时间
    @CreationTimestamp
    @Column(nullable = false)
    private LocalDateTime createAt=LocalDateTime.now ();

    //更新时间
    @CreationTimestamp
    @Column(nullable = false)
    private LocalDateTime updataAt=LocalDateTime.now ();


    // 发送者 不为空
    // 多个消息对应一个发送者
    @JoinColumn(name = "senderId")
    @ManyToOne(optional = false)
    private User sender;
    // 这个字段仅仅只是为了对应sender的数据库字段senderId
    // 不允许手动的更新或者插入
    @Column(nullable = false, updatable = false, insertable = false)
    private String senderId;

    // 接收者 可为空
    // 多个消息对应一个接收者
    @ManyToOne
    @JoinColumn(name = "receiverId")
    private User receiver;
    @Column(updatable = false, insertable = false)
    private String receiverId;

    @ManyToOne
    @JoinColumn(name = "groupId")
    private Group group;
    @Column(updatable = false,insertable = false)
    private String groupId;
}
