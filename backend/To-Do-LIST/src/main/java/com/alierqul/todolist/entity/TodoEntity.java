package com.alierqul.todolist.entity;

import java.util.Date;

import javax.persistence.*;

import org.hibernate.annotations.CreationTimestamp;

import com.alierqul.todolist.entity.UserEntity;

import lombok.Data;

@Data
@Entity
public class TodoEntity {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @Column(length = 255)
  private String todo;
  
  @Temporal(TemporalType.TIMESTAMP)
  @CreationTimestamp
  private Date startDate;

  @ManyToOne
  @JoinColumn(name = "user_id", referencedColumnName = "id")
  private UserEntity user;
  
  private Date finishDate;
  
}
