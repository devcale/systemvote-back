package com.systemvote.systemvoteback.controller;

import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.systemvote.systemvoteback.dto.UserDTO;
import com.systemvote.systemvoteback.model.User;
import com.systemvote.systemvoteback.security.UserPrincipal;
import com.systemvote.systemvoteback.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    private IUserService userService;



    @GetMapping("/user/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Integer id, @AuthenticationPrincipal UserPrincipal principal)
    {
        UserDTO user =  userService.getById(id);
        try
        {
            if(user != null)
            {
                return ResponseEntity.ok((UserDTO) user);
            }
        }catch (Exception e)
        {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }


        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @GetMapping("/user")
    public ResponseEntity<List<UserDTO>> getUsers()
    {
        try{
            return ResponseEntity.ok(userService.getAll());
        } catch (Exception e)
        {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

    }

    @PostMapping("/user")
    public ResponseEntity<String> createUser(@RequestBody User userObj)
    {
        try{
            userService.create(userObj);
            return new ResponseEntity<>("User created:" + userObj, HttpStatus.CREATED);
        } catch (Exception e)
        {
            return new ResponseEntity<>("An error occurred when creating the User:" + e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }

    @PutMapping("/user")
    public ResponseEntity<String> updateUser(@RequestBody User userObj)
    {
        try{
            User updatedUser = userService.update(userObj);
            return new ResponseEntity<>("User updated:" + updatedUser, HttpStatus.OK);
        } catch (Exception e)
        {
            return new ResponseEntity<>("An error occurred when creating the User:" + e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Integer id)
    {
        try{
            userService.delete(id);
            return new ResponseEntity<>("User of id "+id+" successfully deleted.", HttpStatus.NO_CONTENT);
        } catch (Exception e)
        {
            return new ResponseEntity<>("An error occurred when deleting the User:" + e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }
}
