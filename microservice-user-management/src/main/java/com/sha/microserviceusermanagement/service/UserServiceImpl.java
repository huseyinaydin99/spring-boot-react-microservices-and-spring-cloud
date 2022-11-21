package com.sha.microserviceusermanagement.service;

import com.sha.microserviceusermanagement.model.User;
import com.sha.microserviceusermanagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User save(User user){
        user.setPassword(this.passwordEncoder.encode(user.getPassword()));
        this.userRepository.save(user);
        return user;
    }

    @Override
    public User findByUsername(String username){
        return this.userRepository.findByUsername(username).orElse(null);
    }

    @Override
    public List<String> findUsers(List<Long> idList){
        return this.userRepository.findByIdList(idList);
    }
}
