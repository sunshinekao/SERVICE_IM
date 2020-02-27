package net.qiujuer.web.italker.push.factory;

import net.qiujuer.web.italker.push.bean.db.Subject;
import net.qiujuer.web.italker.push.bean.db.User;
import net.qiujuer.web.italker.push.utils.Hib;

import java.util.List;

public class SubjectFactory {
    public static List<Subject> subject(){
        return Hib.query(session -> {

            return (List<Subject>) session.createQuery("from Subject")
                    .setMaxResults(20) // 至多20条
                    .list();

        });
    }
}
