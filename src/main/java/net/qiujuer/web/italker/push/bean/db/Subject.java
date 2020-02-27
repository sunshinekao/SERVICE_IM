package net.qiujuer.web.italker.push.bean.db;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "TB_SUBJECT")
public class Subject {

    @Id
    @PrimaryKeyJoinColumn
    // 主键生成存储的类型为UUID
    @GeneratedValue(generator = "uuid")
    // 把uuid的生成器定义为uuid2，uuid2是常规的UUID toString
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    // 不允许更改，不允许为null
    @Column(updatable = false, nullable = false)
    private String id;

     //课程名
    @Column(nullable = false)
    private String name;

    //教室
    @Column(nullable = false)
    private String room;

   //教师
   @Column(nullable = false)
    private String teacher;

    //起始周
    @Column(nullable = false)
    private int startWeek;

    //上课周数
    @Column(nullable = false)
    private int stepWeek;

    //开始上课的节次
    @Column(nullable = false)
    private int start;

    //上课节数
    @Column(nullable = false)
    private int step;

   //周几上
   @Column(nullable = false)
    private int day;

    //一个随机数，用于对应课程的颜色
    @Column(nullable = false)
    private int colorRandom;
}
