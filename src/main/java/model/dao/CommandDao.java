package model.dao;

import Service.SessionAcces;
import model.Entities.CommandEntity;

/**
 * Created by Xavier on 30/09/2016.
 */
public class CommandDao {
    SessionAcces sessionAcces = new SessionAcces();

    public CommandDao()
    {}

    public void create(CommandEntity commandEntity)
    {
        sessionAcces.getCurrentSession().save(commandEntity);
    }

    public void update(CommandEntity commandEntity)
    {
        sessionAcces.getCurrentSession().update(commandEntity);
    }

    public CommandEntity findById(String id)
    {
        CommandEntity commandEntity = (CommandEntity) sessionAcces.getCurrentSession().get(CommandEntity.class, id);
        return commandEntity;
    }

    public void delete(CommandEntity commandEntity)
    {
        sessionAcces.getCurrentSession().delete(commandEntity);
    }
}
