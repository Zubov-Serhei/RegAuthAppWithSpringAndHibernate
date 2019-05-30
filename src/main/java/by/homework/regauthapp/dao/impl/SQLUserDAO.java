package by.homework.regauthapp.dao.impl;

import by.homework.regauthapp.dao.UserDAO;
import by.homework.regauthapp.entity.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Сергей Зубов on 30.05.2019.
 */
@Repository
public class SQLUserDAO implements UserDAO {

    @Autowired
    private SessionFactory sessionFactory;

    public User authentication(User user) {
        Session session = null;
        try{
            session = sessionFactory.openSession();
            List<User> users = session.createQuery("from User where login = :login")
                    .setParameter("login",user.getLogin())
                    .list();
            if(users!=null&&users.size()>0){
                return users.get(0);
            }
        }catch(HibernateException e){
            System.err.println("SQLUserDAO authentocation "+e.getMessage());
            e.printStackTrace();
        }finally {
            if(session!=null) session.close();
        }
        return null;
    }

    public boolean registration(User user) {
        Session session = null;
        Integer id = null;
        try{
            session = sessionFactory.openSession();
            List<User> users = session.createQuery("from User where login = :login")
                    .setParameter("login",user.getLogin())
                    .list();
            if(users!=null&&users.size()>0){
                return false;
            }
            id = (Integer) session.save(user);
            if(id!=null&&id>=0) return true;
        }catch(HibernateException e){
            System.err.println("SQLUserDAO registration "+e.getMessage());
            e.printStackTrace();
        }finally {
            if(session!=null) session.close();
        }
        return false;
    }

    public List<User> getAllUsers() {
        Session session = null;
        try{
            session = sessionFactory.openSession();
            List<User> users = session.createQuery("from User").list();
            if(users.size()>0){
                return users;
            }
        }catch (HibernateException e){
            System.err.println("SQLUserDAO getAllUsers "+e.getMessage());
            e.printStackTrace();
        }finally {
            if(session!=null)session.close();
        }return null;
    }
}

