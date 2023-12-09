package com.systemvote.systemvoteback.security;

import com.systemvote.systemvoteback.model.User;
import com.systemvote.systemvoteback.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private IUserService userService;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.getByUsername(username);
        return new UserPrincipal(user.getId(), user.getUsername(), user.getPassword(), List.of(new SimpleGrantedAuthority(user.getRole())));

    }


}
