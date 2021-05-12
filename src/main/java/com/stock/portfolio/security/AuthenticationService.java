package com.stock.portfolio.security;

import com.stock.portfolio.models.User;
import com.stock.portfolio.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    @Autowired
    private UserRepository userRepository;

    public Authentication getAuthentication() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.isAuthenticated() && !(auth instanceof AnonymousAuthenticationToken)) {
            return auth;
        }
        throw new AuthenticationServiceException("Authentication Failed");
    }

    public UserDetailsImpl getCurrentUserDetails() {
        if (getAuthentication().getPrincipal() instanceof UserDetailsImpl) {
            return (UserDetailsImpl) getAuthentication().getPrincipal();
        }
        throw new AuthenticationCredentialsNotFoundException("User Details Not Found");
    }

    public User getCurrentUser() {
        UserDetailsImpl userDetails = getCurrentUserDetails();
        User user = userRepository.findByUsername(userDetails.getUsername());

        if (user == null) {
            throw new UsernameNotFoundException("User Not Found");
        }

        return user;
    }
}
