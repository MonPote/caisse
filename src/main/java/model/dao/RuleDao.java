package model.dao;

import Service.SessionAcces;
import model.Entities.ClientEntity;
import model.Entities.RuleEntity;

/**
 * Created by Xavier on 30/09/2016.
 */
public class RuleDao {
    SessionAcces sessionAcces = new SessionAcces();

    public RuleDao()
    {}

    public void create(RuleEntity ruleEntity)
    {
        sessionAcces.getCurrentSession().save(ruleEntity);
    }

    public void update(RuleEntity ruleEntity)
    {
        sessionAcces.getCurrentSession().update(ruleEntity);
    }

    public RuleEntity findById(String id)
    {
        RuleEntity ruleEntity = (RuleEntity) sessionAcces.getCurrentSession().get(RuleEntity.class, id);
        return ruleEntity;
    }

    public void delete(RuleEntity ruleEntity)
    {
        sessionAcces.getCurrentSession().delete(ruleEntity);
    }
}
