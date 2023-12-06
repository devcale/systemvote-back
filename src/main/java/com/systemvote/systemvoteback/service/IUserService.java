package com.systemvote.systemvoteback.service;

import com.systemvote.systemvoteback.dto.UserDTO;
import com.systemvote.systemvoteback.model.User;

import java.util.List;

public interface IUserService {
    List<UserDTO> getAll();

    UserDTO getById(int id);

    void create(User user);

    User update(User user);

    void delete(int id);
}
