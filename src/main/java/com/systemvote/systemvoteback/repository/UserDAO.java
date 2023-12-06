package com.systemvote.systemvoteback.repository;

import com.systemvote.systemvoteback.model.User;
import jakarta.persistence.EntityManager;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDAO implements IUserDAO{

    @Autowired
    private EntityManager entityManager;


    public List<User> getAll() {
        Session currentSession = entityManager.unwrap(Session.class);
        Query<User> query = currentSession.createQuery("FROM User", User.class);
        return query.getResultList();
    }

    public User getById(int id) {
        Session currentSession = entityManager.unwrap(Session.class);
        return currentSession.get(User.class, id);
    }

    @Override
    public void save(User user) {
        Session currentSession = entityManager.unwrap(Session.class);
        currentSession.persist(user);
    }

    public User update(User user) {
        Session currentSession = entityManager.unwrap(Session.class);
        return currentSession.merge(user);
    }

    @Override
    public void delete(int id) {
        Session currentSession = entityManager.unwrap(Session.class);
        User user = currentSession.get(User.class, id);
        currentSession.remove(user);
    }
}
