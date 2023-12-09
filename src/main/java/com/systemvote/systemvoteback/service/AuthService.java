package com.systemvote.systemvoteback.service;

import com.systemvote.systemvoteback.model.LoginResponse;
import com.systemvote.systemvoteback.security.JwtIssuer;
import com.systemvote.systemvoteback.security.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private JwtIssuer jwtIssuer;
    @Autowired
    private AuthenticationManager authenticationManager;

    public LoginResponse attemptLogin(String email, String password) {
        var authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(email, password)
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserPrincipal principal = (UserPrincipal) authentication.getPrincipal();

        JwtIssuer.Request request = new JwtIssuer.Request(principal.getId(), principal.getUsername(), principal.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList());

        String token = jwtIssuer.issue(request);

        return new LoginResponse(token);
    }

}
