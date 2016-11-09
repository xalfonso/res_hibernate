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

    public List<Author> getByFirstSurname(String surname){
        return null;
    }

    public List<Author> getByFirstNameAndFirstSurname(String firstName, String firstSurname){
        return null;
    }

    public boolean changeSecondSurname(String newSecondSurname, String oldSecondSurname) throws Exception{
        try {
            SessionFactory sessionFactory = getSessionFactory();
            Session session = sessionFactory.getCurrentSession();

            session.beginTransaction();
            int n = session.createQuery("UPDATE Author a SET a.secondSurname = '" + newSecondSurname +"' WHERE a.secondSurname = '" + oldSecondSurname +"'").executeUpdate();
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
            int n = session.createQuery("DELETE Author a WHERE a.firstName = '" + firstName +"'").executeUpdate();
            session.getTransaction().commit();

            return (n > 0);
        } catch (HibernateException e) {
            throw new Exception(e);
        }
    }
}
