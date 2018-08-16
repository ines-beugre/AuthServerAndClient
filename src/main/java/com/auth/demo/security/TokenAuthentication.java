package com.auth.demo.security;

import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.*;

public class TokenAuthentication extends AbstractAuthenticationToken {

    private final DecodedJWT jwt;
    private boolean invalidated;
    private String userEmail;
    private String userLastname;
    private String userFirstname;
    private String nickname;
    private String name;

    public TokenAuthentication(DecodedJWT jwt) {
        super(readAuthorities(jwt));
        this.jwt = jwt;
        readClaims();
    }

    public String asJson() {
        return new String(Base64.getDecoder().decode(jwt.getPayload()));
    }

    private void readClaims() {
        Claim userEmail = this.jwt.getClaim("email");
        Claim userFirstname = this.jwt.getClaim("given_name");
        Claim userLastname = this.jwt.getClaim("family_name");
        Claim nickname = this.jwt.getClaim("nickname");
        Claim name = this.jwt.getClaim("name");
        this.userEmail = userEmail != null ? userEmail.asString() : "";
        this.userFirstname = userFirstname != null ? userFirstname.asString() : "";
        this.userLastname = userLastname != null ? userLastname.asString() : "";
        this.name = name != null ? name.asString() : "";
        this.nickname = nickname != null ? nickname.asString() : "";
    }

    private boolean hasExpired() {
        return jwt.getExpiresAt().before(new Date());
    }

    private static Collection<? extends GrantedAuthority> readAuthorities(DecodedJWT jwt) {
        Claim rolesClaim = jwt.getClaim("https://access.control/roles");
        if (rolesClaim.isNull()) {
            return Collections.emptyList();
        }
        List<GrantedAuthority> authorities = new ArrayList<>();
        String[] scopes = rolesClaim.asArray(String.class);
        for (String s : scopes) {
            SimpleGrantedAuthority a = new SimpleGrantedAuthority(s);
            if (!authorities.contains(a)) {
                authorities.add(a);
            }
        }
        System.out.println("authorities" + authorities);
        return authorities;
    }


    @Override
    public String getCredentials() {
        return jwt.getToken();
    }

    @Override
    public Object getPrincipal() {
        return jwt.getSubject();
    }

    @Override
    public void setAuthenticated(boolean authenticated) {
        if (authenticated) {
            throw new IllegalArgumentException("Create a new Authentication object to authenticate");
        }
        invalidated = true;
    }

    @Override
    public boolean isAuthenticated() {
        return !invalidated && !hasExpired();
    }

    private String getUserInfo(){
        Claim payload = jwt.getClaim("given_name");
        return payload.asString();
    }

//    @Override
//    public String toString() {
//        return "TokenAuthentication{" +
//                "name=" + this.userEmail +
//                ", invalidated=" + invalidated +
//                '}';
//    }

    @Override
    public String toString() {
        return name;
    }

}

