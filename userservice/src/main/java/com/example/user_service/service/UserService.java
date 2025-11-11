package com.example.user_service.service;

import com.example.user_service.model.User;
import com.example.user_service.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userrepo;
     
    public User saveUser(User user){
        return userrepo.save(user);
    }
    public List<User> getAllUsers(){
        return userrepo.findAll();
    }
    
    public Optional<User> getUserById(Long id){
        return userrepo.findById(id);
    }
  //jpql query
  public Optional<User> getUsername(String name){
        return userrepo.findUserByName(name);
  }

}
