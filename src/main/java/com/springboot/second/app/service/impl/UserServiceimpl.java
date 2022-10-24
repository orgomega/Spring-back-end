package com.springboot.second.app.service.impl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.springboot.second.app.exeption.ResourceNotFoundExeption;
import com.springboot.second.app.model.User;
import com.springboot.second.app.repository.UserRepository;
import com.springboot.second.app.service.UserService;
@Service
public class UserServiceimpl implements UserService{

	private UserRepository userRepository;
    @Autowired
	public UserServiceimpl(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}

	@Override
	public User saveUser(User user) {	
		return userRepository.save(user);
	}
	@Override
	public User updateUser(User user, Long id) {
		    User existingUser =userRepository.findById(id).orElseThrow(
				() -> new ResourceNotFoundExeption("User","Id",id)	);
		    existingUser.setNom(user.getNom());
		    existingUser.setPrenom(user.getPrenom());
		    existingUser.setUsername(user.getUsername());
		    existingUser.setAdress(user.getAdress());
		    existingUser.setCountry(user.getCountry());
		    existingUser.setCity(user.getCity());
		    existingUser.setCodepostal(user.getCodepostal());
		    existingUser.setEmail(user.getEmail());
			userRepository.save(existingUser);
			return existingUser ;
	}



}
