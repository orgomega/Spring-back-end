package com.springboot.second.app.service.impl;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.springboot.second.app.exeption.ResourceNotFoundExeption;
import com.springboot.second.app.model.Budget;
import com.springboot.second.app.model.Compte;
import com.springboot.second.app.model.User;
import com.springboot.second.app.repository.BudgetRepository;
import com.springboot.second.app.repository.CompteRepository;
import com.springboot.second.app.repository.UserRepository;
import com.springboot.second.app.service.BudgetService;
@Service
public class BudgetServiceimpl implements BudgetService {
	private BudgetRepository budgetRepositry;
	private UserRepository userRepository;
	private CompteRepository compteRepository;
    @Autowired
	public BudgetServiceimpl(BudgetRepository budgetRepositry,UserRepository userRepository,CompteRepository compteRepository) {
		super();
		this.budgetRepositry = budgetRepositry;
		this.userRepository=userRepository;
		this.compteRepository=compteRepository;
	}
    @Override
	@Transactional
	public Budget saveBudget(Budget budget) {
    	long compteId=budget.getCompte().getId();
		long userId =budget.getUser().getId();
		Optional<Compte> compte=compteRepository.findById(compteId);
		Optional<User> user =userRepository.findById(userId);
		System.out.println(compte.get());
		System.out.println(user.get());
		if (user.isEmpty()) {
			throw new ResourceNotFoundExeption("userId","ID",userId) ;
		}
		if (compte.isEmpty()) {
			throw new ResourceNotFoundExeption("compteId","ID",compteId) ;
		}
		budget.setCompte(compte.get());
		budget.setUser(user.get());
		return budgetRepositry.save(budget);
	}
    @Override
	public List<Budget> getALLBudgets(Long user_id ) {
		return budgetRepositry.getBudgets(user_id);
	}
    @SuppressWarnings("deprecation")
	@Override
	public Budget getBudgetByID(Long id) {
		Optional<Budget>budget=Optional.ofNullable(budgetRepositry.getById(id));
		if(budget.isPresent()) {
			return budget.get();
		}else {
		  throw new ResourceNotFoundExeption("Budget","ID",id) ;	
		}
	}
    @Override
	public Budget updateBudget(Budget budget, Long id) {
		long userId =budget.getUser().getId();
		Optional<User> user =userRepository.findById(userId);
		System.out.println(user.get());
		if (user.isEmpty()) {
			throw new ResourceNotFoundExeption("userId","ID",userId) ;
		}
		    Budget existingBudget =budgetRepositry.findById(id).orElseThrow(
				() -> new ResourceNotFoundExeption("Budget","Id",id)	);
		    existingBudget.setCategory(budget.getCategory());
		    existingBudget.setCompte(budget.getCompte());
		    existingBudget.setDuration(budget.getDuration());
		    existingBudget.setName(budget.getName());
		    existingBudget.setValue(budget.getValue());
		    existingBudget.setUser(user.get());
			budgetRepositry.save(existingBudget);
			return existingBudget ;
	}
    @Override
	public void deleteBudget(Long id) {
    	budgetRepositry.findById(id).orElseThrow(
				()-> new ResourceNotFoundExeption("Budget","Id",id)) ;
    	budgetRepositry.deleteById(id);		
	}
}
