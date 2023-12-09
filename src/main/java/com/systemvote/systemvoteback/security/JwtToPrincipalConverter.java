package com.systemvote.systemvoteback.security;

import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class JwtToPrincipalConverter {

    public UserPrincipal convert (DecodedJWT jwt)
    {
        List<SimpleGrantedAuthority> authorityList = Optional.ofNullable(getClaimOrEmptyList(jwt, "au"))
                .orElse(Collections.emptyList())
                .stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
        return new UserPrincipal(Integer.valueOf(jwt.getSubject()), jwt.getClaim("e").asString(), "", authorityList);
    }




    private List<String> getClaimOrEmptyList(DecodedJWT jwt, String claim) {
        if (jwt.getClaim(claim).isNull()) return List.of();
        return jwt.getClaim(claim).asList(String.class);
    }
}
