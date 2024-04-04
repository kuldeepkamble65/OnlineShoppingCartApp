package org.example.Dao;

import org.example.db.DbQueries;
import org.example.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;


    public void addUser(User user) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(user);
        session.getTransaction().commit();

    }

    public User getByEmail(String email) {
        Session session = sessionFactory.openSession();
        Query<User> query = session.createQuery(DbQueries.GET_BY_MAIL, User.class);
        query.setParameter("email", email);
        return query.uniqueResult();
    }


    public User findById(int id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(User.class, id);
        } catch (Exception e) {
            throw new RuntimeException("Error fetching user by ID", e);
        }
    }

    public void updateUser(User user) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.update(user);
            session.getTransaction().commit();
        } catch (Exception e) {
            throw new RuntimeException("Error updating user", e);
        }
    }
}
