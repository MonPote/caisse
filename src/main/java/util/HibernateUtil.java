package util;


import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import java.security.MessageDigest;

/**
 * Created by trang_d on 24/04/15.
 */
public class HibernateUtil {

    public static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            /*
            Configuration configuration = new Configuration();
            configuration.configure("hibernate.cfg.xml");
            StandardServiceRegistryBuilder ssrb = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
            sessionFactory = configuration.buildSessionFactory(ssrb.build());*/
            Configuration configuration = new Configuration().configure();
            StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
            sessionFactory = configuration.buildSessionFactory(builder.build());
        }
        return sessionFactory;
    }

    /**
     * Hash an input string with SHA-256 algorithm
     * @param in The input string to hash
     * @return The hashed string of length 32
     */
    public static String hash32(String in) {
        return hashX(in, 32);
    }

    public static String hashX(String in, int len) {
        try {
            if (len > 0 && len < 63) {
                MessageDigest md = MessageDigest.getInstance("SHA-256");
                md.update(in.getBytes("UTF-8"));
                byte[] result = md.digest();
                StringBuffer sb = new StringBuffer();
                for (int i = 0; i < result.length; ++i)
                    sb.append(Integer.toHexString(result[i] & 0xff));
                return sb.toString().substring(0, len);
            }
        } catch (Exception e) {
            e.printStackTrace(System.err);
        }
        return null;
    }
}
