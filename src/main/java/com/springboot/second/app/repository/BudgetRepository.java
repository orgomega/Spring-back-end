package com.springboot.second.app.repository;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.springboot.second.app.model.Budget;
public interface BudgetRepository extends JpaRepository<Budget, Long>{
	@Query(value = "SELECT * FROM Budgets WHERE user_id = ?1", nativeQuery = true)
	  List<Budget>getBudgets(Long user_id);
}
