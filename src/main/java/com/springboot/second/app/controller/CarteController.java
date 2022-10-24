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
import com.springboot.second.app.model.Carte;
import com.springboot.second.app.service.CarteService;
@CrossOrigin(origins="http://localhost:4200")
@RestController
@RequestMapping("/api/cartes")
public class CarteController {
	private CarteService carteService ;
    @Autowired
    public CarteController(CarteService carteService) {
		super();
		this.carteService = carteService;
	}
    @PostMapping
    public ResponseEntity<Carte> saveCarte(@RequestBody Carte carte){
		return new ResponseEntity<Carte>(carteService.saveCarte(carte), HttpStatus.CREATED);		 
    }
    @GetMapping("/user/{user_id}")
	public List<Carte>getALLCartes(@PathVariable("user_id") Long user_id){
		return carteService.getALLCartes(user_id);
	}
	@GetMapping("{id}")
	public ResponseEntity <Carte> GetCarteById(@PathVariable("id") Long carteid) {
		return new ResponseEntity<Carte>(carteService.getCarteByID(carteid),HttpStatus.OK);
	}
	@PutMapping("{id}")
	public ResponseEntity<Carte>updateCarte(@PathVariable("id") Long id ,@RequestBody Carte carte){    
		return new ResponseEntity<Carte>(carteService.updateCarte(carte, id), HttpStatus.OK);
	}
	@DeleteMapping("{id}")
	public ResponseEntity<String> deleteCarte(@PathVariable("id") Long id){
		  carteService.deleteCarte(id);
          return new ResponseEntity<String>("Carte deleted sucessfully!.",HttpStatus.OK);
	}
}
