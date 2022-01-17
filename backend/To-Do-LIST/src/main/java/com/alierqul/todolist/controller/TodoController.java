package com.alierqul.todolist.controller;

import javax.validation.Valid;

import com.alierqul.todolist.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alierqul.todolist.shared.GenericResponse;
import com.alierqul.todolist.pojo.Todo;
import com.alierqul.todolist.entity.configure.CurrentUser;
import com.alierqul.todolist.entity.UserEntity;


@RestController
@RequestMapping("/api/1.0")
public class TodoController {
  
  @Autowired
  TodoService service;

  @PostMapping("/todo")
  GenericResponse saveTodo(@Valid @RequestBody Todo todo, @CurrentUser UserEntity user) {
    service.save(todo, user);
      return new GenericResponse("Hoax is saved");
  }
  
  @GetMapping("/users/{username}/todo") 
  Page<Todo> getUserTodo(@PathVariable String username, @PageableDefault(sort = "startDate", direction = Direction.DESC) Pageable page){
      return service.getTodoOfUser(username, page).map(Todo::new);
  }

}