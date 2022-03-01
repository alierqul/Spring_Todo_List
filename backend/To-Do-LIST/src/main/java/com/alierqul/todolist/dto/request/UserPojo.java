package com.alierqul.todolist.dto.request;

import com.alierqul.todolist.repository.entity.UserEntity;

import lombok.Data;

@Data
public class UserPojo {
  private String username;
  
  private String name;
  
  private String image;
  
  public UserPojo(UserEntity user) {
      this.setUsername(user.getUsername());
      this.setName(user.getName());
      this.setImage(user.getImage());
  }
}
