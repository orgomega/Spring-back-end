package com.springboot.second.app.repository;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.springboot.second.app.model.Revenue;

public interface RevenueRepository extends JpaRepository<Revenue, Long>{
	@Query(value = "SELECT * FROM Revenues WHERE user_id = ?1", nativeQuery = true)
	  List<Revenue>getRevenues(Long user_id);
}
