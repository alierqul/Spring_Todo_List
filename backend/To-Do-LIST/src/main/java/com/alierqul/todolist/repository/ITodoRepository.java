package com.alierqul.todolist.repository;

import com.alierqul.todolist.entity.TodoEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.alierqul.todolist.entity.UserEntity;



public interface ITodoRepository extends JpaRepository<TodoEntity, Long>, JpaSpecificationExecutor<TodoEntity>{
    
    Page<TodoEntity> findByUser(UserEntity user, Pageable page);

}


