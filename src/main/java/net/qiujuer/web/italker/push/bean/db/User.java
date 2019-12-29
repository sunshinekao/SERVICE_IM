package net.qiujuer.web.italker.push.bean.db;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * 用户的Model，对应数据库
 */
@Entity
@Table(name="USER")
public class User {
    @Id
    @PrimaryKeyJoinColumn
    //主键生成存储的类型为UUID
    @GeneratedValue(generator = "uuid")
    @GenericGenerator (name="uuid",strategy = "uuid2")
    @Column(updatable = false,nullable = false)
    private String id;

    @Column(nullable = false,length = 128,unique = true)
    private String name;

    @Column(nullable = false,length = 62,unique = true)
    private String phone;

    @Column(nullable = false)
    private String password;

    //头像可以为null
    @Column
    private String portrait;

    @Column
    private String description;

    @Column(nullable = false)
    private int sex=0;

    //token唯一，也可以拉取用户信息
    @Column(unique = true)
    private String token;

    //用于推送
    @Column
    private String pushId;

    //创建时间
    @CreationTimestamp
    @Column(nullable = false)
    private LocalDateTime createAt=LocalDateTime.now ();

    //更新时间
    @CreationTimestamp
    @Column(nullable = false)
    private LocalDateTime updataAt=LocalDateTime.now ();

    //最后一次收到消息时间
    @Column()
    private LocalDateTime lastReceiveAt=LocalDateTime.now ();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPortrait() {
        return portrait;
    }

    public void setPortrait(String portrait) {
        this.portrait = portrait;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getPushId() {
        return pushId;
    }

    public void setPushId(String pushId) {
        this.pushId = pushId;
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public void setCreateAt(LocalDateTime createAt) {
        this.createAt = createAt;
    }

    public LocalDateTime getUpdataAt() {
        return updataAt;
    }

    public void setUpdataAt(LocalDateTime updataAt) {
        this.updataAt = updataAt;
    }

    public LocalDateTime getLastReceiveAt() {
        return lastReceiveAt;
    }

    public void setLastReceiveAt(LocalDateTime lastReceiveAt) {
        this.lastReceiveAt = lastReceiveAt;
    }
}
