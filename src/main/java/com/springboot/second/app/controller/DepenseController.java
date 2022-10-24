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
import com.springboot.second.app.model.Depense;
import com.springboot.second.app.service.DepenseService;
@CrossOrigin(origins="http://localhost:4200")
@RestController
@RequestMapping("/api/depenses")
public class DepenseController {
	    private DepenseService depenseService ;
	    @Autowired
	    public DepenseController(DepenseService depenseService) {
			super();
			this.depenseService = depenseService;
		}
	    @PostMapping
	    public ResponseEntity<Depense> saveDepense(@RequestBody Depense depense){
			return new ResponseEntity<Depense>(depenseService.saveDepense(depense), HttpStatus.CREATED);		 
	    }
	    @GetMapping("/user/{user_id}")
		public List<Depense>getALLDepenses(@PathVariable("user_id") Long user_id){
			return depenseService.getALLDepenses(user_id);
		}
	//http://localhost:8090/api/depenses/1
		@GetMapping("{id}")
		public ResponseEntity <Depense> GetDepenseById(@PathVariable("id") Long depenseid) {
			return new ResponseEntity<Depense>(depenseService.getDepenseByID(depenseid),HttpStatus.OK);
		}
	//http://localhost:8090/api/depenses/1
		@PutMapping("{id}")
		public ResponseEntity<Depense>updateDepense(@PathVariable("id") Long id ,@RequestBody Depense depense){    
			return new ResponseEntity<Depense>(depenseService.updateDepense(depense, id), HttpStatus.OK);
		}
		@DeleteMapping("{id}")
		public ResponseEntity<String> deleteDepense(@PathVariable("id") Long id){
			  depenseService.deleteDepense(id);
	          return new ResponseEntity<String>("Product deleted sucessfully!.",HttpStatus.OK);
		}
		

}
