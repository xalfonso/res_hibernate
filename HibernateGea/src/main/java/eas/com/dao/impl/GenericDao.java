package eas.com.dao.impl;

import eas.com.dao.InterfaceDao;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

/**
 * Created by eduardo on 11/7/2016.
 */
abstract public class GenericDao<T> implements InterfaceDao<T>, AutoCloseable {

    private static SessionFactory sessionFactory;
    private Class<T> tClass;


    public GenericDao(Class<T> tClass) {
        this.tClass = tClass;
    }

    static SessionFactory getSessionFactory() throws HibernateException {
        if (sessionFactory == null) {
            sessionFactory = buildSessionFactory();
        }
        return sessionFactory;
    }


    private static SessionFactory buildSessionFactory() throws HibernateException {
        return new Configuration().configure(/* default: "hibernate.cfg.xml"*/).buildSessionFactory();
    }

    public void insert(T t) throws Exception {
        try {
            SessionFactory sessionFactory = getSessionFactory();
            Session session = sessionFactory.getCurrentSession();

            session.beginTransaction();

            session.save(t);

            session.getTransaction().commit();

        } catch (HibernateException e) {
            throw new Exception(e);
        }
    }


    @Override
    public void update(T t) throws Exception {
        try {
            SessionFactory sessionFactory = getSessionFactory();
            Session session = sessionFactory.getCurrentSession();

            session.beginTransaction();
            session.merge(t);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            throw new Exception(e);
        }
    }

    /**
     * @param t
     * @throws Exception
     */
    @Override
    public void delete(T t) throws Exception {
        try {
            SessionFactory sessionFactory = getSessionFactory();
            Session session = sessionFactory.getCurrentSession();

            session.beginTransaction();
            session.delete(t);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            throw new Exception(e);
        }
    }


    @Override
    public void delete(long id) throws Exception {
        try {
            SessionFactory sessionFactory = getSessionFactory();
            Session session = sessionFactory.getCurrentSession();

            session.beginTransaction();
            T t = session.load(this.tClass, id);
            session.delete(t);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            throw new Exception(e);
        }
    }

    @Override
    public T get(Long id) throws Exception {
        try {
            SessionFactory sessionFactory = getSessionFactory();
            Session session = sessionFactory.getCurrentSession();

            session.beginTransaction();
            T t = session.get(this.tClass, id);
            session.getTransaction().commit();
            return t;
        } catch (HibernateException e) {
            throw new Exception(e);
        }
    }


    @Override
    public List<T> getAll() throws Exception {
        try {
            SessionFactory sessionFactory = getSessionFactory();
            Session session = sessionFactory.getCurrentSession();

            session.beginTransaction();
            List<T> tList = session.createQuery("FROM " + this.tClass.getName()).list();
            session.getTransaction().commit();
            return tList;
        } catch (HibernateException e) {
            throw new Exception(e);
        }
    }


    @Override
    public void close()  {
        getSessionFactory().getCurrentSession().close();
        getSessionFactory().close();
    }
}
