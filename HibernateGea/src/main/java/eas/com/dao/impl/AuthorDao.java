package eas.com.dao.impl;

import eas.com.entity.Author;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

/**
 * Created by eduardo on 11/7/2016.
 */
public class AuthorDao extends GenericDao<Author>{

    public AuthorDao() {
        super(Author.class);
    }


    public List<Author> getLikeFirstSurname(String firstSurname) throws Exception{

        try {
            SessionFactory sessionFactory = getSessionFactory();
            Session session = sessionFactory.getCurrentSession();

            session.beginTransaction();
            List<Author> authorList = session.createQuery("FROM Author a WHERE a.firstSurname LIKE '%" + firstSurname +"%'").list();
            session.getTransaction().commit();
            return authorList;
        } catch (HibernateException e) {
            throw new Exception(e);
        }

    }

    public List<Author> getByFirstSurname(String firstSurname) throws Exception{
        try {
            SessionFactory sessionFactory = getSessionFactory();
            Session session = sessionFactory.getCurrentSession();

            session.beginTransaction();
            List<Author> authorList = session.createQuery("FROM Author a WHERE a.firstSurname = :firstSurname")
                    .setParameter("firstSurname", firstSurname)
                    .getResultList();
            session.getTransaction().commit();
            return authorList;
        } catch (HibernateException e) {
            throw new Exception(e);
        }
    }

    public List<Author> getByFirstNameAndFirstSurname(String firstName, String firstSurname) throws Exception{
        try {
            SessionFactory sessionFactory = getSessionFactory();
            Session session = sessionFactory.getCurrentSession();

            session.beginTransaction();
            List<Author> authorList = session.createQuery("FROM Author a WHERE a.firstSurname = :firstSurname  AND a.firstName = :firstName")
                    .setParameter("firstName", firstName)
                    .setParameter("firstSurname", firstSurname)
                    .getResultList();
            session.getTransaction().commit();
            return authorList;
        } catch (HibernateException e) {
            throw new Exception(e);
        }
    }

    public boolean changeSecondSurname(String newSecondSurname, String oldSecondSurname) throws Exception{
        try {
            SessionFactory sessionFactory = getSessionFactory();
            Session session = sessionFactory.getCurrentSession();

            session.beginTransaction();
            int n = session.createQuery("UPDATE Author a SET a.secondSurname = :newSecondSurname  WHERE a.secondSurname = :oldSecondSurname ")
                    .setParameter("newSecondSurname", newSecondSurname)
                    .setParameter("oldSecondSurname", oldSecondSurname)
                    .executeUpdate();
            session.getTransaction().commit();

            return (n > 0);
        } catch (HibernateException e) {
            throw new Exception(e);
        }
    }

    public boolean deleteAuthorByFirstName(String firstName) throws Exception{
        try {
            SessionFactory sessionFactory = getSessionFactory();
            Session session = sessionFactory.getCurrentSession();

            session.beginTransaction();
            int n = session.createQuery("DELETE Author a WHERE a.firstName = :firstName ")
                    .setParameter("firstName", firstName)
                    .executeUpdate();
            session.getTransaction().commit();

            return (n > 0);
        } catch (HibernateException e) {
            throw new Exception(e);
        }
    }
}
