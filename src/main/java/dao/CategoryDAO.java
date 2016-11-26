package dao;

import model.CategoryEntity;
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
public class CategoryDAO {
    public List<CategoryEntity> listCategories() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Criteria criteria = session.createCriteria(model.CategoryEntity.class);
        ArrayList<model.CategoryEntity> categories = (ArrayList<model.CategoryEntity>) criteria
                .addOrder(Order.asc("name"))
                .list();
        session.close();
        return categories;
    }

    public List<CategoryEntity> listCategoriesById(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Criteria criteria = session.createCriteria(CategoryEntity.class);
        ArrayList<CategoryEntity> categories = (ArrayList<CategoryEntity>) criteria
                .add(Restrictions.eq("id", id))
                .addOrder(Order.asc("name"))
                .list();
        session.close();
        return categories;
    }

    public List<CategoryEntity> listCategoriesByName(String name) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Criteria criteria = session.createCriteria(CategoryEntity.class);
        ArrayList<CategoryEntity> categories = (ArrayList<CategoryEntity>) criteria
                .add(Restrictions.like("name", name))
                .addOrder(Order.asc("name"))
                .list();
        session.close();
        return categories;
    }


    public void addCategory(CategoryEntity s) throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(s);
        transaction.commit();
        session.close();
    }

    public void removeCategory(CategoryEntity s) throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(s);
        transaction.commit();
        session.close();
    }

    public void updateCategory(CategoryEntity s) throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.update(s);
        transaction.commit();
        session.close();
    }
}
