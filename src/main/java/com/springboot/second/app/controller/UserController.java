package com.springboot.second.app.controller;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.view.RedirectView;

import com.springboot.second.app.model.Compte;
import com.springboot.second.app.model.User;
import com.springboot.second.app.service.CompteService;
import com.springboot.second.app.service.UserService;
@ResponseBody
@Controller
@RequestMapping(value = "user")
//@CrossOrigin(origins="http://localhost:4200")
public class UserController {
	private UserService userService ;
	private CompteService compteService;
	
    @Autowired
    public UserController(UserService userService,CompteService compteService) {
		super();
		this.userService = userService;
		this.compteService=compteService;
	}
	@PostMapping
    public RedirectView saveUser(User user,BindingResult result, ModelMap model){
	String sha256hex = DigestUtils.sha1Hex(user.getPassword());
    user.setPassword(sha256hex);
    userService.saveUser(user);
    Compte compte=new Compte();
    compte.setBalance("0");
    compte.setCurrency("Dt");
    compte.setName("Portefeuille");
    compte.setUser(user);
    compteService.saveCompte(compte);
	RedirectView redirectView = new RedirectView();
    redirectView.setUrl("http://localhost:4200");
    return redirectView;
    }
	@PutMapping("{id}")
	public ResponseEntity<User>updateUser(@PathVariable("id") Long id ,@RequestBody User user){    
		return new ResponseEntity<User>(userService.updateUser(user, id), HttpStatus.OK);
	}
}
