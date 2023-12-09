package com.systemvote.systemvoteback.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Component
public class JwtIssuer {

    @Autowired
    private JwtProperties properties;
    public String issue(Request request){
        return JWT.create()
                .withSubject(String.valueOf(request.getUserId()))
                .withExpiresAt(Instant.now().plus(Duration.of(1, ChronoUnit.DAYS)))
                .withClaim("e", request.getEmail())
                .withClaim("a", request.getRoles())
                .sign(Algorithm.HMAC256(properties.getSecretKey()));

    }


    public static class Request {
        private final int userId;
        private final String email;
        private final List<String> roles;

        public Request(int userId, String email, List<String> roles) {
            this.userId = userId;
            this.email = email;
            this.roles = roles;
        }

        public int getUserId() {
            return userId;
        }

        public String getEmail() {
            return email;
        }

        public List<String> getRoles() {
            return roles;
        }
    }
}
