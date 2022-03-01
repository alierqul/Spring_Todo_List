package com.alierqul.todolist.service;

import com.alierqul.todolist.repository.entity.TodoEntity;
import com.alierqul.todolist.repository.ITodoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.alierqul.todolist.repository.IUserRepository;
import com.alierqul.todolist.repository.entity.UserEntity;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
public class TodoService implements IServiceCrudProgress<TodoEntity> {

  private ITodoRepository todoRepository;
  private IUserRepository userService;

  public TodoService(ITodoRepository todoRepository, IUserRepository userService) {
    super();
    this.todoRepository = todoRepository;
    this.userService = userService;
  }


  @Override
  public TodoEntity create(TodoEntity entity) {
    return  todoRepository.save(entity);
  }

  public TodoEntity create(TodoEntity entity,String username) {
    UserEntity user=userService.findByUsername(username);
    entity.setUser(user);
    return  create(entity);
  }

  @Override
  public TodoEntity update(long id,TodoEntity entity) {
    TodoEntity todo=getById(id);
    todo.setId(entity.getId());
    todo.setTodo(entity.getTodo());
    todo.setStartDate(entity.getStartDate());
    todo.setFinishDate(entity.getFinishDate());
    return todoRepository.save(entity);
  }

  @Override
  public Page<TodoEntity> getAllItem(Pageable page, TodoEntity entity) {

    return todoRepository.findAll(page);
  }

  @Override
  public TodoEntity getById(long id) {
    Optional<TodoEntity> optDB=todoRepository.findById(id);
    if(!optDB.isPresent()){
      throw new IllegalArgumentException("Not found id"+id);
    }
    return optDB.get();
  }

  @Override
  public void delete(long id) {
    todoRepository.deleteById(id);
  }


  public Page<TodoEntity> getTodoOfUser(String username, Pageable page) {
    UserEntity inDBUser = userService.findByUsername(username);
    return todoRepository.findByUser(inDBUser,page);
  }

  public void doneTodo(long todoID) {
    TodoEntity inDB=getById(todoID);
    if(inDB.getFinishDate()<=0){
      inDB.setFinishDate(System.currentTimeMillis());
    }else{
      inDB.setFinishDate(-1);
    }

    todoRepository.save(inDB);
  }
}
