package model.dao;

import Service.SessionAcces;
import model.Entities.ClientEntity;
import model.Entities.CouponEntity;

/**
 * Created by Xavier on 30/09/2016.
 */
public class CouponDao {
    SessionAcces sessionAcces = new SessionAcces();

    public CouponDao() {
    }

    public void create(CouponEntity couponEntity) {
        sessionAcces.getCurrentSession().save(couponEntity);
    }

    public void update(CouponEntity couponEntity) {
        sessionAcces.getCurrentSession().update(couponEntity);
    }

    public CouponEntity findById(String id) {
        CouponEntity couponEntity = (CouponEntity) sessionAcces.getCurrentSession().get(CouponEntity.class, id);
        return couponEntity;
    }

    public void delete(CouponEntity couponEntity) {
        sessionAcces.getCurrentSession().delete(couponEntity);
    }
}

