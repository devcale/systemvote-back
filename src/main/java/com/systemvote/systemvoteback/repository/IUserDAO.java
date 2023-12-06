package com.systemvote.systemvoteback.repository;

import com.systemvote.systemvoteback.model.User;

import java.util.List;

public interface IUserDAO {
    List<User> getAll();

    User getById(int id);

    void save(User user);

    User update(User user);

    void delete(int id);
}
