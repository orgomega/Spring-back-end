package com.springboot.second.app.repository;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.springboot.second.app.model.Depense;
public interface DepenseRepository extends JpaRepository<Depense, Long>{
 
	@Query(value = "SELECT * FROM Depenses WHERE user_id = ?1", nativeQuery = true)
	  List<Depense>getDepenses(Long user_id);
}
