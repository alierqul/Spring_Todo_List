package com.alierqul.todolist.repository.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TodoEntity implements Serializable {
  private static final long serialVersionUID = 9066911574538657025L;
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @Column(length = 255)
  private String todo;

  private long startDate=System.currentTimeMillis();

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id", referencedColumnName = "id")
  private UserEntity user;
  
  private long finishDate;
  
}
