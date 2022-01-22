package com.alierqul.todolist.configure;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.alierqul.todolist.repository.IUserRepository;
import com.alierqul.todolist.entity.UserEntity;


@Service
public class UserAuthService implements UserDetailsService {
  
  @Autowired
  IUserRepository userRepository;

private static final Logger log = LoggerFactory.getLogger(UserAuthService.class);

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    UserEntity inDB= userRepository.findByUsername(username);
    if(inDB==null) {
      log.warn("User Not Found");
      throw new UsernameNotFoundException("User not found");
    }
    return inDB;
  }

 
}
