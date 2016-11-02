package model.dao;

import Service.SessionAcces;
import model.Entities.ClientEntity;
import model.Entities.ProductEntity;

/**
 * Created by Xavier on 30/09/2016.
 */
public class ProductDao {
    SessionAcces sessionAcces = new SessionAcces();

    public ProductDao()
    {}

    public void create(ProductEntity productEntity)
    {
        sessionAcces.getCurrentSession().save(productEntity);
    }

    public void update(ProductEntity productEntity)
    {
        sessionAcces.getCurrentSession().update(productEntity);
    }

    public ProductEntity findById(String id)
    {
        ProductEntity productEntity = (ProductEntity) sessionAcces.getCurrentSession().get(ProductEntity.class, id);
        return productEntity;
    }

    public void delete(ProductEntity productEntity)
    {
        sessionAcces.getCurrentSession().delete(productEntity);
    }
}
