package com.alierqul.todolist.pojo;

import com.alierqul.todolist.entity.UserEntity;

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
