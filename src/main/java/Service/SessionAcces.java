package Service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

/**
 * Created by Xavier on 30/09/2016.
 */
public class SessionAcces {

    private org.hibernate.Session currentSession;
    private Transaction currentTransaction;

    public SessionAcces()
    {}

    private static SessionFactory getSessionFactory()
    {
        Configuration configuration = new Configuration().configure();
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
        SessionFactory sessionFactory = configuration.buildSessionFactory(builder.build());
        return sessionFactory;
    }

    public void setCurrentTransaction(Transaction currentTransaction) {
        this.currentTransaction = currentTransaction;
    }

    public Transaction getCurrentTransaction() {
        return currentTransaction;
    }

    public void setCurrentSession(org.hibernate.Session currentSession) {
        this.currentSession = currentSession;
    }

    public org.hibernate.Session getCurrentSession() {
        return currentSession;
    }
    public org.hibernate.Session openCurrentSession()
    {
        currentSession = getSessionFactory().openSession();
        return currentSession;
    }
}
