package com.heartkid.oauth;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import com.heartkid.service.DirectoryService;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

 
    @Autowired
    DirectoryService directoryService;

    public Authentication authenticate(Authentication authentication)
            throws AuthenticationException {
System.out.println("username--->"+authentication.getName());
        Authentication auth = null;
        String heartkidnumber = authentication.getName();
        String password = authentication.getCredentials().toString();
        System.out.println("Login Credential : " + heartkidnumber + password);
        boolean loginState = directoryService.con(heartkidnumber, password);
        if (loginState) {
            List<GrantedAuthority> grantedAuths = new ArrayList<GrantedAuthority>();
            grantedAuths.add(new SimpleGrantedAuthority("ROLE_USER"));
            auth = new UsernamePasswordAuthenticationToken(heartkidnumber,
                    password, grantedAuths);
            return auth;
        } else {
            return auth;
        }
    }

    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

}
