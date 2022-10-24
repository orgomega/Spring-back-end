package com.springboot.second.app.repository;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.springboot.second.app.model.Carte;
public interface CarteRepository extends JpaRepository<Carte, Long>{
	@Query(value = "SELECT * FROM Cartes WHERE user_id = ?1", nativeQuery = true)
	  List<Carte>getCartes(Long user_id);
}
