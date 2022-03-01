package com.alierqul.todolist.controller;

import javax.validation.Valid;

import com.alierqul.todolist.repository.entity.configure.CurrentUser;
import com.alierqul.todolist.repository.entity.UserEntity;
import com.alierqul.todolist.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alierqul.todolist.dto.response.GenericResponse;
import com.alierqul.todolist.dto.request.UserPojo;
import com.alierqul.todolist.dto.request.UserUpdate;

@RestController
@RequestMapping("/api/1.0")
public class UserController {


	@Autowired	
	private UserService userService;

	// @CrossOrigin // 3000 portundan 8080 portuna json gönderdiğimiz için aldığımız
	// hatayı bu
	// Annotation ile düzeltiyoruz.
	// proxy ile url ayarllarını düzelttiğimizde gerek kalmadı.

	// @Valid ile Springden Rica ediyoruz. Hibernate e sor bakalım user tablo için
	// geçerli bir nesne
	// mi?
	
	// @ResponseStatus(HttpStatus.CREATED) // Default 200 Ok dir. Burda bir create
	// işlemi yaptığımız için
	// 201 Created dönüyoruz.

	@PostMapping("/users")
	public GenericResponse createUser(@Valid @RequestBody UserEntity user) {
		userService.create(user);
		return new GenericResponse("user Created");
	}
	
	@GetMapping("/users")
	public Page<UserPojo> getUser(Pageable page,@CurrentUser UserEntity user ){
		 
		return userService.getAllItem(page,user).map(UserPojo::new);
	}
	
	@GetMapping("/users/{username}")
	public UserPojo getSingleUser(@PathVariable("username") String username) {
		UserEntity user=userService.getByUsername(username);
		return new UserPojo(user);
	}
	
	@PutMapping("/users/{username}")
	public UserPojo updateUser(@RequestBody UserUpdate updateUser,@PathVariable("username") String username) {
		UserEntity inDB=userService.getByUsername(username);
		inDB.setName(updateUser.getName());
		UserEntity user =userService.update(inDB.getId(),inDB);
		return new UserPojo(user);
	}

}
