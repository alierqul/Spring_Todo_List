package com.alierqul.todolist.service;

import com.alierqul.todolist.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IServiceCrudProgress <T> {
    T create(T entity);
    T update(long id,T entity);
    Page<T> getAllItem(Pageable page, T user);
    T getById(long id);
    void delete(long id);
}
