package dao;

import model.StoreEntity;
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
public class StoreDAO {
    public List<StoreEntity> listStores() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Criteria criteria = session.createCriteria(StoreEntity.class);
        ArrayList<StoreEntity> stores = (ArrayList<StoreEntity>) criteria
                .addOrder(Order.asc("name"))
                .list();
        session.close();
        return stores;
    }

    public List<StoreEntity> listStoresById(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Criteria criteria = session.createCriteria(StoreEntity.class);
        ArrayList<StoreEntity> stores = (ArrayList<StoreEntity>) criteria
                .add(Restrictions.eq("id", id))
                .addOrder(Order.asc("name"))
                .list();
        session.close();
        return stores;
    }

    public List<StoreEntity> listStoresByName(String name) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Criteria criteria = session.createCriteria(StoreEntity.class);
        ArrayList<StoreEntity> stores = (ArrayList<StoreEntity>) criteria
                .add(Restrictions.like("name", name))
                .addOrder(Order.asc("name"))
                .list();
        session.close();
        return stores;
    }


    public void addStore(StoreEntity s) throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(s);
        transaction.commit();
        session.close();
    }

    public void removeStore(StoreEntity s) throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(s);
        transaction.commit();
        session.close();
    }

    public void updateStore(StoreEntity s) throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.update(s);
        transaction.commit();
        session.close();
    }
}
