package com.springboot.second.app.service;
import java.util.List;

import com.springboot.second.app.model.Carte;
public interface CarteService {
	Carte saveCarte(Carte carte);
	List<Carte>getALLCartes(Long user_id);
	Carte getCarteByID(Long id);
	Carte updateCarte(Carte carte,Long id );
    void deleteCarte(Long id);
}
