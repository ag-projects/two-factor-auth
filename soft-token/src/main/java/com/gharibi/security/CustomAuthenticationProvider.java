package com.gharibi.security;

import com.gharibi.persistence.UserRepository;
import com.gharibi.web.model.User;
import org.jboss.aerogear.security.otp.Totp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private UserRepository userRepository;


    @Override
    public Authentication authenticate(Authentication auth) throws AuthenticationException {
        String username = auth.getName();
        String password = auth.getCredentials().toString();
        String verificationCode = ((CustomWebAuthenticationDetails) auth.getDetails()).getVerificationCode();

        User user = this.userRepository.findByEmail(username);
        if(user == null) {
            throw new BadCredentialsException("Invalid username or password");
        }

        Totp totp = new Totp(user.getSecret());
        try {
            if(!totp.verify(verificationCode)) {
                throw new BadCredentialsException("Invalid verification code");
            }
        } catch (Exception e) {
            throw new BadCredentialsException("Invalid verification code");
        }

        return new UsernamePasswordAuthenticationToken(
                user, password, Arrays.asList(new SimpleGrantedAuthority("ROLE_USER")));
    }

    @Override
    public boolean supports(Class<?> auth) {
        return auth.equals(UsernamePasswordAuthenticationToken.class);
    }
}
