package ursi.backoffice.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Created by Xavier on 30/09/2016.
 */
public class HibernateUtil {

    private static final SessionFactory sessionFactory;
     static {
         try {
          sessionFactory = new Configuration().configure().buildSessionFactory();
         } catch (Throwable ex)
         {
             System.err.println("Echec création SessionFactory" + ex);
             throw new ExceptionInInitializerError(ex);
         }
     }

    public static SessionFactory getSessionFactory()
    {
        return sessionFactory;
    }
}
