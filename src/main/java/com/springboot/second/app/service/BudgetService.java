package com.springboot.second.app.service;
import java.util.List;
import com.springboot.second.app.model.Budget;
public interface BudgetService {
	Budget saveBudget(Budget budget);
	List<Budget>getALLBudgets(Long user_id);
	Budget getBudgetByID(Long id);
	Budget updateBudget(Budget budget,Long id );
    void deleteBudget(Long id);
}
