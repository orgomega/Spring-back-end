package com.springboot.second.app.service;

import java.util.List;
import com.springboot.second.app.model.Compte;
public interface CompteService {
	Compte saveCompte(Compte compte);
	List<Compte>getALLComptes(Long user_id);
	Compte getCompteByID(Long id);
	Compte updateCompte(Compte compte,Long id );
    void deleteCompte(Long id);

}
