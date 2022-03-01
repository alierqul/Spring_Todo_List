package com.alierqul.todolist.repository.entity.configure;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.alierqul.todolist.repository.IUserRepository;
import com.alierqul.todolist.repository.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;

public class UniqeUsernameValidator implements ConstraintValidator<UniqeUsername, String> {

  @Autowired
  IUserRepository userRepository;

  @Override
  public boolean isValid(String username, ConstraintValidatorContext context) {
    UserEntity user = userRepository.findByUsername(username);
    return user != null ? false : true;
  }

}
