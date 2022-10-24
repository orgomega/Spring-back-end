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
import com.springboot.second.app.model.Budget;
import com.springboot.second.app.service.BudgetService;
@CrossOrigin(origins="http://localhost:4200")
@RestController
@RequestMapping("/api/budgets")
public class BudgetController {
	private BudgetService budgetService ;
    @Autowired
    public BudgetController(BudgetService budgetService) {
		super();
		this.budgetService = budgetService;
	}
    @PostMapping
    public ResponseEntity<Budget> saveBudget(@RequestBody Budget budget){
		return new ResponseEntity<Budget>(budgetService.saveBudget(budget), HttpStatus.CREATED);		 
    }
    @GetMapping("/user/{user_id}")
	public List<Budget>getALLBudgets(@PathVariable("user_id") Long user_id){
		return budgetService.getALLBudgets(user_id);
	}
	@GetMapping("{id}")
	public ResponseEntity <Budget> GetBudgetById(@PathVariable("id") Long budgetid) {
		return new ResponseEntity<Budget>(budgetService.getBudgetByID(budgetid),HttpStatus.OK);
	}
	@PutMapping("{id}")
	public ResponseEntity<Budget>updateBudget(@PathVariable("id") Long id ,@RequestBody Budget budget){    
		return new ResponseEntity<Budget>(budgetService.updateBudget(budget, id), HttpStatus.OK);
	}
	@DeleteMapping("{id}")
	public ResponseEntity<String> deleteBudget(@PathVariable("id") Long id){
		budgetService.deleteBudget(id);
          return new ResponseEntity<String>("Budget deleted sucessfully!.",HttpStatus.OK);
	}
}
