package dao;

import model.ProductEntity;
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
public class ProductDAO {
    public List<ProductEntity> listProducts() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Criteria criteria = session.createCriteria(ProductEntity.class);
        ArrayList<ProductEntity> products = (ArrayList<ProductEntity>) criteria
                .addOrder(Order.asc("reference"))
                .list();
        session.close();
        return products;
    }

    public List<ProductEntity> listProductsById(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Criteria criteria = session.createCriteria(ProductEntity.class);
        ArrayList<ProductEntity> products = (ArrayList<ProductEntity>) criteria
                .add(Restrictions.eq("id", id))
                .addOrder(Order.asc("reference"))
                .list();
        session.close();
        return products;
    }

    public List<ProductEntity> listProductsByRef(String ref) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Criteria criteria = session.createCriteria(ProductEntity.class);
        ArrayList<ProductEntity> products = (ArrayList<ProductEntity>) criteria
                .add(Restrictions.like("reference", ref))
                .addOrder(Order.asc("reference"))
                .list();
        session.close();
        return products;
    }


    public void addProduct(ProductEntity s) throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(s);
        transaction.commit();
        session.close();
    }

    public void removeProduct(ProductEntity s) throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(s);
        transaction.commit();
        session.close();
    }

    public void updateProduct(ProductEntity s) throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.update(s);
        transaction.commit();
        session.close();
    }
}
