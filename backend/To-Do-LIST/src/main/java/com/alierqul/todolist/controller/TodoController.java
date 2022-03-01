package com.alierqul.todolist.controller;

import javax.validation.Valid;

import com.alierqul.todolist.dto.request.GetTodoByIdDto;
import com.alierqul.todolist.repository.entity.TodoEntity;
import com.alierqul.todolist.service.TodoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.alierqul.todolist.dto.response.GenericResponse;
import com.alierqul.todolist.dto.request.Todo;


@RestController
@RequestMapping("/api/1.0")
@Slf4j
public class TodoController {
  
  @Autowired
  TodoService service;

  @PostMapping("/{username}/todo")
  GenericResponse saveTodo( @PathVariable("username") String username,@Valid @RequestBody TodoEntity todo) {

    service.create(todo, username);
      return new GenericResponse(username+" Todo is saved");
  }
  
  @GetMapping("/{username}/todo")
  Page<Todo> getTodoByUser(@PathVariable("username") String username, @PageableDefault(sort = "startDate", direction = Direction.DESC) Pageable page){
      return service.getTodoOfUser(username, page).map(Todo::new);
  }

  @PutMapping("todo/{todoid}")
  TodoEntity updateTodo(@PathVariable("todoid") long todoID,@RequestBody TodoEntity todo){

    return service.update(todoID,todo);

  }

  @DeleteMapping("/{username}/todo")
  HttpStatus deleteTodoByUser(@PathVariable("username") String username,@RequestBody @Valid GetTodoByIdDto getTodoByIdDto){

    service.delete(getTodoByIdDto.getTodoID());
    return HttpStatus.OK;
  }

  @PutMapping("/{username}/todo")
  HttpStatus doneTodoByUser(@PathVariable("username") String username,@RequestBody @Valid GetTodoByIdDto getTodoByIdDto){

    service.doneTodo(getTodoByIdDto.getTodoID());
    return HttpStatus.OK;
  }

}
