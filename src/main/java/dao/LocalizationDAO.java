package dao;

import model.LocalizationEntity;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import util.HibernateUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nea on 30/09/16.
 */
public class LocalizationDAO {
    public List<LocalizationEntity> listLocalizations() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Criteria criteria = session.createCriteria(LocalizationEntity.class);
        ArrayList<LocalizationEntity> localizations = (ArrayList<LocalizationEntity>) criteria
                .addOrder(Order.asc("name"))
                .list();
        session.close();
        return localizations;
    }

    public List<LocalizationEntity> listLocalizationsById(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Criteria criteria = session.createCriteria(LocalizationEntity.class);
        ArrayList<LocalizationEntity> localizations = (ArrayList<LocalizationEntity>) criteria
                .add(Restrictions.eq("id", id))
                .addOrder(Order.asc("name"))
                .list();
        session.close();
        return localizations;
    }

    public List<LocalizationEntity> listLocalizationsByName(String name) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Criteria criteria = session.createCriteria(LocalizationEntity.class);
        ArrayList<LocalizationEntity> localizations = (ArrayList<LocalizationEntity>) criteria
                .add(Restrictions.like("name", name))
                .addOrder(Order.asc("name"))
                .list();
        session.close();
        return localizations;
    }


    public void addLocalization(LocalizationEntity s) throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(s);
        transaction.commit();
        session.close();
    }

    public void removeLocalization(LocalizationEntity s) throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(s);
        transaction.commit();
        session.close();
    }

    public void updateLocalization(LocalizationEntity s) throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.update(s);
        transaction.commit();
        session.close();
    }
}
