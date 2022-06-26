package store.bookstore.config;

import java.util.ArrayList;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import store.bookstore.controller.errors.UsernameInvalidException;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if ("javyn".equals(username)) {
            return new User("javyn", "$2a$10$/AnOvhp3I6Php34Azcl8A.deyNjV3TQZPvSlAbQaD.9M449uZA81K",
                    new ArrayList<>());
        } else {
            throw new UsernameInvalidException("User not found with username: " + username);
        }
    }
}