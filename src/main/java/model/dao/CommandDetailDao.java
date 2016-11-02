package model.dao;

import Service.SessionAcces;
import model.Entities.ClientEntity;
import model.Entities.CommandDetailEntity;

/**
 * Created by Xavier on 30/09/2016.
 */
public class CommandDetailDao {
    SessionAcces sessionAcces = new SessionAcces();

    public CommandDetailDao()
    {}

    public void create(CommandDetailEntity commandDetailEntity)
    {
        sessionAcces.getCurrentSession().save(commandDetailEntity);
    }

    public void update(CommandDetailEntity commandDetailEntity)
    {
        sessionAcces.getCurrentSession().update(commandDetailEntity);
    }

    public CommandDetailEntity findById(String id)
    {
        CommandDetailEntity commandDetailEntity = (CommandDetailEntity) sessionAcces.getCurrentSession().get(CommandDetailEntity.class, id);
        return commandDetailEntity;
    }

    public void delete(CommandDetailEntity commandDetailEntity)
    {
        sessionAcces.getCurrentSession().delete(commandDetailEntity);
    }
}
