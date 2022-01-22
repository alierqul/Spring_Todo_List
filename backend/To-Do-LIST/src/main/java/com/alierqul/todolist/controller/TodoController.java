package com.alierqul.todolist.controller;

import javax.validation.Valid;

import com.alierqul.todolist.entity.TodoEntity;
import com.alierqul.todolist.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.alierqul.todolist.shared.GenericResponse;
import com.alierqul.todolist.pojo.Todo;
import com.alierqul.todolist.entity.configure.CurrentUser;
import com.alierqul.todolist.entity.UserEntity;


@RestController
@RequestMapping("/api/1.0")
public class TodoController {
  
  @Autowired
  TodoService service;

  @PostMapping("/{username}/todo")
  GenericResponse saveTodo( @PathVariable("username") String username,@Valid @RequestBody TodoEntity todo) {

    service.create(todo, username);
      return new GenericResponse(username+" Todo is saved");
  }
  
  @GetMapping("/{username}/todo")
  Page<Todo> getTodoByUser(@PathVariable("username") String username,  Pageable page){
      return service.getTodoOfUser(username, page).map(Todo::new);
  }

  @PutMapping("todo/{todoid}")
  TodoEntity updateTodo(@PathVariable("username") long todoID,@RequestBody TodoEntity todo){

    return service.update(todoID,todo);

  }

  @DeleteMapping("/todo/{todoid}")
  HttpStatus deleteTodoByUser(@PathVariable("todoid")long todoid){
    service.delete(todoid);
    return HttpStatus.OK;
  }

}
