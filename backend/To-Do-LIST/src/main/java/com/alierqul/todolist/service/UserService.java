package com.alierqul.todolist.service;




import com.alierqul.todolist.repository.IUserRepository;
import com.alierqul.todolist.repository.entity.TodoEntity;
import com.alierqul.todolist.repository.entity.UserEntity;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.mapping.Collection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.alierqul.todolist.error.NotFoundException;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


@Service
@Transactional
@Slf4j
public class UserService implements IServiceCrudProgress <UserEntity> {



  private IUserRepository userRepository;
  private PasswordEncoder passwordEncoder;

  public UserService(IUserRepository userRepository, PasswordEncoder passwordEncoder) {
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
  }


	@Override
	public UserEntity create(UserEntity entity) {
		entity.setPassword(this.passwordEncoder.encode(entity.getPassword()));
		return userRepository.save(entity);
	}

	@Override
	public UserEntity update(long id,UserEntity entity) {

		return userRepository.save(entity);
	}

	@Override
	public Page<UserEntity> getAllItem(Pageable page, UserEntity user) {
		if(user!=null) {
			return userRepository.findByUsernameNot(user.getUsername(), page);
		}
		return userRepository.findAll(page);
	}

	@Override
	public UserEntity getById(long id) {
	  return userRepository.findById(id).get();

	}

	@Override
	public void delete(long id) {
		userRepository.deleteById(id);
	}

	public UserEntity getByUsername(String username) {
		UserEntity inDB = userRepository.findByUsername(username);
		if (inDB == null) {
			throw new NotFoundException();
		}
		return inDB;
	}

	public Page<TodoEntity> getAllUserTodosSortedByStartDateDesc(UserEntity user,Pageable page){
		List<TodoEntity> todos=user.getTodos().stream()
				.sorted(Comparator.comparingLong(TodoEntity::getStartDate).reversed())
				.collect(Collectors.toList());

		Page<TodoEntity> pages = new PageImpl(todos, page, todos.size());
		return pages;
	}


}
