package com.alierqul.todolist.pojo;


import java.util.Date;

import com.alierqul.todolist.entity.TodoEntity;
import com.alierqul.todolist.entity.UserEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Todo {
 
  private long id;
  private String todo; 
  private Date startDate;
  private UserEntity user;
  private Date finishDate;
  
  public Todo(TodoEntity todo) {
    
    this.setId(todo.getId());   
    this.setTodo(todo.getTodo());
    this.setStartDate(todo.getStartDate());
    this.setUser(todo.getUser());
    this.setFinishDate(todo.getFinishDate());
  }
  
  
}

