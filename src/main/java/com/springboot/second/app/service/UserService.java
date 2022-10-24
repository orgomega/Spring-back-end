package com.springboot.second.app.service;
import com.springboot.second.app.model.User;

public interface UserService {
	User saveUser(User user);
	User updateUser(User user,Long id );
}
