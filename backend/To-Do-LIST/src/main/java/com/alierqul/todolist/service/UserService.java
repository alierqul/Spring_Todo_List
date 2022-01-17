package com.alierqul.todolist.service;




import com.alierqul.todolist.repository.IUserRepository;
import com.alierqul.todolist.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.alierqul.todolist.error.NotFoundException;
import com.alierqul.todolist.pojo.UserUpdate;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
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
	public UserEntity update(UserEntity entity) {

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

}
