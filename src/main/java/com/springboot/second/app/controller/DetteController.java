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
import com.springboot.second.app.model.Dette;
import com.springboot.second.app.service.DetteService;
@CrossOrigin(origins="http://localhost:4200")
@RestController
@RequestMapping("/api/dettes")
public class DetteController {
	
	private DetteService detteService;
    @Autowired
    public DetteController(DetteService detteService) {
		super();
		this.detteService = detteService;
	}
    @PostMapping
    public ResponseEntity<Dette> saveDette(@RequestBody Dette dette){
		return new ResponseEntity<Dette>(detteService.saveDette(dette), HttpStatus.CREATED);		 
    }
    @GetMapping("/user/{user_id}")
	public List<Dette>getALLDettes(@PathVariable("user_id") Long user_id){
		return detteService.getALLDettes(user_id);
	}
	@GetMapping("{id}")
	public ResponseEntity <Dette> GetDetteById(@PathVariable("id") Long detteid) {
		return new ResponseEntity<Dette>(detteService.getDetteByID(detteid),HttpStatus.OK);
	}
	@PutMapping("{id}")
	public ResponseEntity<Dette>updateDette(@PathVariable("id") Long id ,@RequestBody Dette dette){    
		return new ResponseEntity<Dette>(detteService.updateDette(dette, id), HttpStatus.OK);
	}
	@DeleteMapping("{id}")
	public ResponseEntity<String> deleteDette(@PathVariable("id") Long id){
		  detteService.deleteDette(id);
          return new ResponseEntity<String>("Dette deleted sucessfully!.",HttpStatus.OK);
	}
}
