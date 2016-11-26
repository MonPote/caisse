package dao;

import model.BrandEntity;
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
public class BrandDAO {
    public List<BrandEntity> listBrands() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Criteria criteria = session.createCriteria(model.BrandEntity.class);
        ArrayList<BrandEntity> brands = (ArrayList<model.BrandEntity>) criteria
                .addOrder(Order.asc("name"))
                .list();
        session.close();
        return brands;
    }

    public List<BrandEntity> listBrandsById(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Criteria criteria = session.createCriteria(BrandEntity.class);
        ArrayList<BrandEntity> brands = (ArrayList<BrandEntity>) criteria
                .add(Restrictions.eq("id", id))
                .addOrder(Order.asc("name"))
                .list();
        session.close();
        return brands;
    }

    public List<BrandEntity> listBrandsByName(String name) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Criteria criteria = session.createCriteria(BrandEntity.class);
        ArrayList<BrandEntity> brands = (ArrayList<BrandEntity>) criteria
                .add(Restrictions.like("name", name))
                .addOrder(Order.asc("name"))
                .list();
        session.close();
        return brands;
    }


    public void addBrand(BrandEntity s) throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(s);
        transaction.commit();
        session.close();
    }

    public void removeBrand(BrandEntity s) throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(s);
        transaction.commit();
        session.close();
    }

    public void updateBrand(BrandEntity s) throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.update(s);
        transaction.commit();
        session.close();
    }
}
