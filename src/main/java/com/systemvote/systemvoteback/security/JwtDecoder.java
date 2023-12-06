package com.systemvote.systemvoteback.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class JwtDecoder {

    @Autowired
    private JwtProperties jwtProperties;
    public DecodedJWT decode(String token) throws SignatureVerificationException
    {
        try{
            return JWT.require(Algorithm.HMAC256(jwtProperties.getSecretKey())).build().verify(token);
        }catch(SignatureVerificationException e)
        {
            return null;
        }

    }
}
