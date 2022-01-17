package com.alierqul.todolist.repository;


import com.alierqul.todolist.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepository extends JpaRepository<UserEntity, Long> {
  
  UserEntity   findByUsername(String username);
  

  
  Page<UserEntity> findByUsernameNot(String username,Pageable page);
}


//@Query(value="SELECT u FROM UserEntity u")
//Page<IUserProjection> getAllUserProjection(Pageable page);
