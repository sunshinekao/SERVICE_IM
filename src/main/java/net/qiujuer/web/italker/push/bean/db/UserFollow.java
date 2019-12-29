package net.qiujuer.web.italker.push.bean.db;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

/**
 * 用户关系Model
 */
@Entity
@Table(name="User_Follow")
public class UserFollow {
    @Id
    @PrimaryKeyJoinColumn
    //主键生成存储的类型为UUID
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name="uuid",strategy = "uuid2")
    @Column(updatable = false,nullable = false)
    private String id;

    //发起人，水友
    @ManyToOne(optional = false)//不可选必修有这个字段
    @JoinColumn(name = "originId")
    private User origin;
    @Column(nullable = false,insertable = false,updatable = false)
    private String originId;

    //目标，UP主
    @ManyToOne(optional = false)//不可选必修有这个字段
    @JoinColumn(name = "targetId")
    private User target;
    @Column(nullable = false,insertable = false,updatable = false)
    private String targetId;

    //别名
    @Column
    private String alias;

    //创建时间
    @CreationTimestamp
    @Column(nullable = false)
    private LocalDateTime createAt=LocalDateTime.now ();

    //更新时间
    @CreationTimestamp
    @Column(nullable = false)
    private LocalDateTime updataAt=LocalDateTime.now ();

    //获取我关注的人列表
    @JoinColumn( name = "originId")
    @LazyCollection (LazyCollectionOption.EXTRA)
    @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private Set<UserFollow> following =new HashSet<> ();

    //获取关注我的人列表
    @JoinColumn( name = "targetId")
    @LazyCollection (LazyCollectionOption.EXTRA)
    @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private Set<UserFollow> follower =new HashSet<> ();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public User getOrigin() {
        return origin;
    }

    public void setOrigin(User origin) {
        this.origin = origin;
    }

    public String getOriginId() {
        return originId;
    }

    public void setOriginId(String originId) {
        this.originId = originId;
    }

    public User getTarget() {
        return target;
    }

    public void setTarget(User target) {
        this.target = target;
    }

    public String getTargetId() {
        return targetId;
    }

    public void setTargetId(String targetId) {
        this.targetId = targetId;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
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
}
