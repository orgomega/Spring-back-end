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
import com.springboot.second.app.model.Revenue;
import com.springboot.second.app.service.RevenueService;
@CrossOrigin(origins="http://localhost:4200")
@RestController
@RequestMapping("/api/revenues")
public class RevenueController {
	private RevenueService revenueService ;
    @Autowired
    public RevenueController(RevenueService revenueService) {
		super();
		this.revenueService = revenueService;
	}
    @PostMapping
    public ResponseEntity<Revenue> saveRevenue(@RequestBody Revenue revenue){
		return new ResponseEntity<Revenue>(revenueService.saveRevenue(revenue), HttpStatus.CREATED);		 
    }
    @GetMapping("/user/{user_id}")
	public List<Revenue>getALLDepenses(@PathVariable("user_id") Long user_id){
		return revenueService.getALLRevenues(user_id);
	}
//http://localhost:8080/api/revenues/1
	@GetMapping("{id}")
	public ResponseEntity <Revenue> GetRevenueById(@PathVariable("id") Long revenueid) {
		return new ResponseEntity<Revenue>(revenueService.getRevenueByID(revenueid),HttpStatus.OK);
	}
//http://localhost:8080/api/revenues/1
	@PutMapping("{id}")
	public ResponseEntity<Revenue>updateRevenue(@PathVariable("id") Long id ,@RequestBody Revenue revenue){    
		return new ResponseEntity<Revenue>(revenueService.updateRevenue(revenue, id), HttpStatus.OK);
	}
	@DeleteMapping("{id}")
	public ResponseEntity<String> deleteRevenue(@PathVariable("id") Long id){
		  revenueService.deleteRevenue(id);
          return new ResponseEntity<String>("Revenue deleted sucessfully!.",HttpStatus.OK);
	}
}
