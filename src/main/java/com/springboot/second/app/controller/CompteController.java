package com.springboot.second.app.controller;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.springboot.second.app.model.Compte;
import com.springboot.second.app.service.CompteService;
@CrossOrigin(origins="http://localhost:4200")
@RestController
@RequestMapping("/api/comptes")
public class CompteController {
	private CompteService compteService ;
	@Autowired
	public CompteController(CompteService compteService) {
		super();
		this.compteService = compteService;
	}
	
	@PostMapping
	public ResponseEntity <Compte> saveCompte(@RequestBody Compte compte){
		return new ResponseEntity<Compte>(compteService.saveCompte(compte), HttpStatus.CREATED);
	}
	@GetMapping("/user/{user_id}")
	public List<Compte>getALLDepenses(@PathVariable("user_id") Long user_id){
		return compteService.getALLComptes(user_id);
	}
	@GetMapping("{id}")
	public ResponseEntity <Compte> GetCompteById(@PathVariable("id") Long compteid) {
		return new ResponseEntity<Compte>(compteService.getCompteByID(compteid),HttpStatus.OK);
	}
	@PutMapping("{id}")
	public ResponseEntity<Compte>updateCompte(@PathVariable("id") Long id ,@RequestBody Compte compte){    
		return new ResponseEntity<Compte>(compteService.updateCompte(compte, id), HttpStatus.OK);
	}
	@DeleteMapping("{id}")
	public ResponseEntity<String> deleteCompte(@PathVariable("id") Long id){
		compteService.deleteCompte(id);
           return new ResponseEntity<String>("Compte deleted sucessfully!.",HttpStatus.OK);
	}
}
