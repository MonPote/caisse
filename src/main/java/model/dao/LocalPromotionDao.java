package model.dao;

import Service.SessionAcces;
import model.Entities.ClientEntity;
import model.Entities.LocalPromotionEntity;

/**
 * Created by Xavier on 30/09/2016.
 */
public class LocalPromotionDao {
    SessionAcces sessionAcces = new SessionAcces();

    public LocalPromotionDao()
    {}

    public void create(LocalPromotionEntity localPromotionEntity)
    {
        sessionAcces.getCurrentSession().save(localPromotionEntity);
    }

    public void update(LocalPromotionEntity localPromotionEntity)
    {
        sessionAcces.getCurrentSession().update(localPromotionEntity);
    }

    public LocalPromotionEntity findById(String id)
    {
        LocalPromotionEntity localPromotionEntity = (LocalPromotionEntity) sessionAcces.getCurrentSession().get(LocalPromotionEntity.class, id);
        return localPromotionEntity;
    }

    public void delete(LocalPromotionEntity localPromotionEntity)
    {
        sessionAcces.getCurrentSession().delete(localPromotionEntity);
    }
}
