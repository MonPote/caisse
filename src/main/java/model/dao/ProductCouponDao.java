package model.dao;

import Service.SessionAcces;
import model.Entities.ClientEntity;
import model.Entities.ProductCouponEntity;

/**
 * Created by Xavier on 30/09/2016.
 */
public class ProductCouponDao {
    SessionAcces sessionAcces = new SessionAcces();

    public ProductCouponDao()
    {}

    public void create(ProductCouponEntity productCouponEntity)
    {
        sessionAcces.getCurrentSession().save(productCouponEntity);
    }

    public void update(ProductCouponEntity productCouponEntity)
    {
        sessionAcces.getCurrentSession().update(productCouponEntity);
    }

    public ProductCouponEntity findById(String id)
    {
        ProductCouponEntity productCouponEntity = (ProductCouponEntity) sessionAcces.getCurrentSession().get(ProductCouponEntity.class, id);
        return productCouponEntity;
    }

    public void delete(ProductCouponEntity productCouponEntity)
    {
        sessionAcces.getCurrentSession().delete(productCouponEntity);
    }
}
