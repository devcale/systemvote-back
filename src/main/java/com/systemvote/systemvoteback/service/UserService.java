package com.systemvote.systemvoteback.service;

import com.systemvote.systemvoteback.dto.UserDTO;
import com.systemvote.systemvoteback.model.User;
import com.systemvote.systemvoteback.repository.UserDAO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService implements IUserService{

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private ModelMapper modelMapper;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();


    @Transactional
    public UserDTO getById(int id) {
        User user = userDAO.getById(id);
        if(user == null)
        {
            return  null;
        }

        return modelMapper.map(user, UserDTO.class);
    }

    @Override
    public UserDTO getDTOByUsername(String username) {
        User user = userDAO.getByUsername(username);
        if(user == null)
        {
            return  null;
        }

        return modelMapper.map(user, UserDTO.class);
    }

    @Override
    public User getByUsername(String username) {
        return userDAO.getByUsername(username);
    }

    @Transactional
    public List<UserDTO> getAll() {

        List<User> users = userDAO.getAll();
        List<UserDTO> userDTOs = new ArrayList<>();

        for(User user : users)
        {
            UserDTO userDTO = modelMapper.map(user, UserDTO.class);
            userDTOs.add(userDTO);
        }

        return userDTOs;

    }




    @Transactional
    public void create(User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        userDAO.save(user);
    }

    @Transactional
    public User update(User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        return userDAO.update(user);
    }

    @Transactional
    @Override
    public void delete(int id) {
        userDAO.delete(id);
    }

    @Override
    public String getRole(int id) {
        return null;
    }


}
