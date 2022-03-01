package com.alierqul.todolist.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alierqul.todolist.repository.entity.configure.CurrentUser;
import com.alierqul.todolist.repository.entity.UserEntity;
import com.alierqul.todolist.dto.request.UserPojo;

@RestController
public class AuthController {

	// required = false parametrenin verilmesinin zorlunlu olduğunu ifade eder
	// değişken
	@PostMapping("/api/1.0/auth")
	public UserPojo handleAuthentication(@CurrentUser UserEntity user) {

		return new UserPojo(user);
	}

}
