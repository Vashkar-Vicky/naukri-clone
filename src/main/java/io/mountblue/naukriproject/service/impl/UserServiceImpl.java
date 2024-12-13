package io.mountblue.naukriproject.service.impl;

import io.mountblue.naukriclone.entity.User;
import io.mountblue.naukriclone.repository.UserRepository;
import io.mountblue.naukriclone.service.UserService;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User getAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.isAuthenticated() &&
                !(authentication instanceof AnonymousAuthenticationToken)) {
            String userEmail = authentication.getName();

            return getUserByUserEmail(userEmail);
        } else {
            throw new RuntimeException("No authenticated user found");
        }
    }

    private User getUserByUserEmail(String email) {
        return (User) userRepository.findUserByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("No user found with email: " + email));
    }

}
