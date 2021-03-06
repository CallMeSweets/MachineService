package com.configuration.machine.services;

import com.configuration.machine.dao.UserRepository;
import com.configuration.machine.security.models.User;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Log4j2
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;


    public void createUser(User user){
        log.trace("inside createUser method");
        userRepository.save(user);
    }


    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        log.trace("inside UserService::loadUserByUsername");
        Optional<User> user = userRepository.findByUsername(username);

        if (user.isPresent()){
            return user.get();
        }else{
            log.trace("User not founded, username: ", username);
            throw new UsernameNotFoundException(String.format("Username[%s] not found"));
        }
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
