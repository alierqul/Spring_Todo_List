package com.alierqul.todolist.dto.request;


import java.util.Date;

import com.alierqul.todolist.repository.entity.TodoEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Todo {
 
  private long id;
  private String todo; 
  private long startDate;
  private long finishDate;
  
  public Todo(TodoEntity todo) {
    
    this.setId(todo.getId());   
    this.setTodo(todo.getTodo());
    this.setStartDate(todo.getStartDate());
    this.setFinishDate(todo.getFinishDate());
  }
  
  
}

