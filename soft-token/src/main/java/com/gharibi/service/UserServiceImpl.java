package com.gharibi.service;

import com.gharibi.persistence.UserRepository;
import com.gharibi.web.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Transactional
@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User registerNewUser(User user) throws Exception {
        if(emailExist(user.getEmail())) {
            throw new IllegalArgumentException("There is an account with that email address: " + user.getEmail());
        }
        return this.userRepository.save(user);
    }

    private boolean emailExist(String email) {
        final User user = userRepository.findByEmail(email);
        return user != null;
    }
}
