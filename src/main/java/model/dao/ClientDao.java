package model.dao;

import Service.SessionAcces;
import model.Entities.ClientEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.stat.Statistics;
import ursi.backoffice.util.HibernateUtil;

import java.io.Serializable;

/**
 * Created by claebo_c on 30/09/16.
 */
public class ClientDao {

    SessionAcces sessionAcces = new SessionAcces();

    public ClientDao()
    {}

    public void create(ClientEntity clientEntity)
    {
        sessionAcces.getCurrentSession().save(clientEntity);
    }

    public void update(ClientEntity clientEntity)
    {
        sessionAcces.getCurrentSession().update(clientEntity);
    }

    public ClientEntity findById(String id)
    {
        ClientEntity clientEntity = (ClientEntity) sessionAcces.getCurrentSession().get(ClientEntity.class, id);
        return clientEntity;
    }

    public void delete(ClientEntity clientEntity)
    {
        sessionAcces.getCurrentSession().delete(clientEntity);
    }
}
