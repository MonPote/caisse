package model.dao;

import Service.SessionAcces;
import model.Entities.ClientEntity;
import model.Entities.ProductPromotionEntity;

/**
 * Created by Xavier on 30/09/2016.
 */
public class ProductPromotionDao {
    SessionAcces sessionAcces = new SessionAcces();

    public ProductPromotionDao()
    {}

    public void create(ProductPromotionEntity productPromotionEntity)
    {
        sessionAcces.getCurrentSession().save(productPromotionEntity);
    }

    public void update(ProductPromotionEntity productPromotionEntity)
    {
        sessionAcces.getCurrentSession().update(productPromotionEntity);
    }

    public ProductPromotionEntity findById(String id)
    {
        ProductPromotionEntity productPromotionEntity = (ProductPromotionEntity) sessionAcces.getCurrentSession().get(ProductPromotionEntity.class, id);
        return productPromotionEntity;
    }

    public void delete(ProductPromotionEntity productPromotionEntity)
    {
        sessionAcces.getCurrentSession().delete(productPromotionEntity);
    }
}
