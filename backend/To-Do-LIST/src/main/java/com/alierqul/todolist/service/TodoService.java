package com.alierqul.todolist.service;

import com.alierqul.todolist.entity.TodoEntity;
import com.alierqul.todolist.repository.ITodoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.alierqul.todolist.pojo.Todo;
import com.alierqul.todolist.repository.IUserRepository;
import com.alierqul.todolist.entity.UserEntity;

@Service
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

  @Override
  public TodoEntity update(TodoEntity entity) {
    return todoRepository.save(entity);
  }

  @Override
  public Page<TodoEntity> getAllItem(Pageable page, TodoEntity entity) {

    return todoRepository.findAll(page);
  }

  @Override
  public TodoEntity getById(long id) {
    return todoRepository.getById(id);
  }

  @Override
  public void delete(long id) {
    todoRepository.deleteById(id);
  }

  public void save(Todo todo, String username) {
    UserEntity user=userService.findByUsername(username);
    TodoEntity entity = new TodoEntity();
    entity.setTodo(todo.getTodo());
    entity.setUser(user);
      create(entity);

  }

  public Page<TodoEntity> getTodoOfUser(String username, Pageable page) {
    UserEntity inDB = userService.findByUsername(username);
    return todoRepository.findByUser(inDB, page);
  }
}
