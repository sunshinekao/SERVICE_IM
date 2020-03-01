package net.qiujuer.web.italker.push.factory;

import net.qiujuer.web.italker.push.bean.db.Subject;
import net.qiujuer.web.italker.push.bean.db.User;
import net.qiujuer.web.italker.push.utils.Hib;

import java.util.List;

public class SubjectFactory {
    public static String subject(){
        String json="[[\"2019-2020学年春\", \"\", \"\", \"计算机组成原理\", \"\", \"\", \"\", \"\", \"刘静\", \"\", \"\", \"1周上\", 1, 1, 2, \"\", \"计算机综合楼106\", \"\"]," +
                "[\"2017-2018学年秋\", \"\", \"\", \"hahaha\", \"\", \"\", \"\", \"\", \"刘静\", \"\", \"\", \"2周上\", 1, 1, 4, \"\", \"计算机综合楼106\", \"\"],"+
                " [\"2017-2018学年秋\", \"\", \"\", \"算法分析与设计\", \"\", \"\", \"\", \"\", \"王静\", \"\", \"\", \"1周\", 1, 3, 2, \"\", \"计算机综合楼205\", \"\"], [\"2017-2018学年秋\", \"\", \"\", \"毛泽东思想和中国特色社会主义理论体系概论\", \"\", \"\", \"\", \"\", \"杨晓军\", \"\", \"\", \"6-12,14-17周上\", 1, 5, 2, \"\", \"3号教学楼3208\", \"\"]," +
                " [\"2017-2018学年秋\", \"\", \"\", \"高等数学提高\", \"\", \"\", \"\", \"\", \"彭波\", \"\", \"\", \"3-12周\", 1, 9, 2, \"\", \"3号教学楼3101\", \"\"], [\"2017-2018学年秋\", \"\", \"\", \"面向对象分析与设计\", \"\", \"\", \"\", \"\", \"马永强\", \"\", \"\", \"1-8周\", 2, 1, 2, \"\", \"计算机综合楼106\", \"\"], [\"2017-2018学年秋\", \"\", \"\", \"软件工程\", \"\", \"\", \"\", \"\", \"马永强\", \"\", \"\", \"6-12,14-18周上\", 2, 3, 2, \"\", \"计算机综合楼106\", \"\"], [\"2017-2018学年秋\", \"\", \"\", \"Linux原理与应用\", \"\", \"\", \"\", \"\", \"刘永利\", \"\", \"\", \"9-12,14-15周上\", 2, 9, 2, \"\", \"计算机综合楼205\", \"\"], [\"2017-2018学年秋\", \"\", \"\", \"计算机组成原理\", \"\", \"\", \"\", \"\", \"刘静\", \"\", \"\", \"8-12,14-17周上\", 3, 1, 2, \"\", \"计算机综合楼106\", \"\"], [\"2017-2018学年秋\", \"\", \"\", \"算法分析与设计\", \"\", \"\", \"\", \"\", \"王静\", \"\", \"\", \"1-12周\", 3, 3, 2, \"\", \"计算机综合楼205\", \"\"], [\"2017-2018学年秋\", \"\", \"\", \"毛泽东思想和中国特色社会主义理论体系概论\", \"\", \"\", \"\", \"\", \"杨晓军\", \"\", \"\", \"6-12,14-17周上\", 3, 5, 2, \"\", \"3号教学楼3208\", \"\"], [\"2017-2018学年秋\", \"\", \"\", \"高等数学提高\", \"\", \"\", \"\", \"\", \"彭波\", \"\", \"\", \"3-4周上\", 3, 7, 2, \"\", \"3号教学楼3101\", \"\"], [\"2017-2018学年秋\", \"\", \"\", \"数据库高级应用\", \"\", \"\", \"\", \"\", \"李国斌\", \"\", \"\", \"9-12,14-18周上\", 3, 9, 2, \"\", \"计算机综合楼202\", \"\"], [\"2017-2018学年秋\", \"\", \"\", \"面向对象分析与设计\", \"\", \"\", \"\", \"\", \"马永强\", \"\", \"\", \"1-8周\", 4, 1, 2, \"\", \"计算机综合楼106\", \"\"], [\"2017-2018学年秋\", \"\", \"\", \"数字图像处理\", \"\", \"\", \"\", \"\", \"王静\", \"\", \"\", \"1-10周\", 4, 3, 2, \"\", \"计算机综合楼102\", \"\"], [\"2017-2018学年秋\", \"\", \"\", \"数据库高级应用\", \"\", \"\", \"\", \"\", \"李国斌\", \"\", \"\", \"9-12,14-18周上\", 4, 5, 2, \"\", \"计算机综合楼202\", \"\"], [\"2017-2018学年秋\", \"\", \"\", \"高等数学提高\", \"\", \"\", \"\", \"\", \"彭波\", \"\", \"\", \"3-12周\", 4, 7, 2, \"\", \"3号教学楼3101\", \"\"], [\"2017-2018学年秋\", \"\", \"\", \"Linux原理与应用\", \"\", \"\", \"\", \"\", \"刘永利\", \"\", \"\", \"9-12,14-15周上\", 4, 9, 2, \"\", \"计算机综合楼205\", \"\"], [\"2017-2018学年秋\", \"\", \"\", \"计算机组成原理\", \"\", \"\", \"\", \"\", \"刘静\", \"\", \"\", \"8-12,14-18周上\", 5, 1, 2, \"\", \"计算机综合楼106\", \"\"], [\"2017-2018学年秋\", \"\", \"\", \"软件工程\", \"\", \"\", \"\", \"\", \"马永强\", \"\", \"\", \"6-12,14-18周上\", 5, 3, 2, \"\", \"计算机综合楼106\", \"\"], [\"2017-2018学年秋\", \"\", \"\", \"毛泽东思想和中国特色社会主义理论体系概论\", \"\", \"\", \"\", \"\", \"杨晓军\", \"\", \"\", \"6-12,14-17周上\", 5, 5, 2, \"\", \"3号教学楼3208\", \"\"], [\"2017-2018学年秋\", \"\", \"\", \"高等数学提高\", \"\", \"\", \"\", \"\", \"彭波\", \"\", \"\", \"3-12周\", 5, 7, 2, \"\", \"3号教学楼3101\", \"\"], [\"2017-2018学年秋\", \"\", \"\", \"数字图像处理\", \"\", \"\", \"\", \"\", \"王静\", \"\", \"\", \"1-10周\", 5, 9, 2, \"\", \"计算机综合楼102\", \"\"], [\"2017-2018学年秋\", \"\", \"\", \"形势与政策-4\", \"\", \"\", \"\", \"\", \"孔祥增\", \"\", \"\", \"9周上\", 7, 5, 4, \"\", \"3号教学楼3311\", \"\"]]";
        return json;
    }
}
