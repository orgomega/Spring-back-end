package com.springboot.second.app.service.impl;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.second.app.exeption.ResourceNotFoundExeption;
import com.springboot.second.app.model.Compte;
import com.springboot.second.app.model.Depense;
import com.springboot.second.app.model.User;
import com.springboot.second.app.repository.CompteRepository;
import com.springboot.second.app.repository.DepenseRepository;
import com.springboot.second.app.repository.UserRepository;
import com.springboot.second.app.service.DepenseService;
@Service
public class DepenseServiceimpl implements DepenseService{
	private DepenseRepository depenseRepositry;
	private UserRepository userRepository;
	private CompteRepository compteRepository;
    @Autowired
	public DepenseServiceimpl(DepenseRepository depenseRepositry,UserRepository userRepository,CompteRepository compteRepository) {
		super();
		this.depenseRepositry = depenseRepositry;
		this.userRepository=userRepository;
		this.compteRepository=compteRepository;
	}
	@Override
	@Transactional
	public Depense saveDepense(Depense depense) {
		long compteId=depense.getCompte().getId();
		long userId =depense.getUser().getId();
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
		depense.setCompte(compte.get());
		depense.setUser(user.get());
		return depenseRepositry.save(depense);
	}
	@Override
	public List<Depense> getALLDepenses(Long user_id ) {
		return depenseRepositry.getDepenses(user_id);
	}

	@SuppressWarnings("deprecation")
	@Override
	public Depense getDepenseByID(Long id) {
		Optional<Depense>depense=Optional.ofNullable(depenseRepositry.getById(id));
		if(depense.isPresent()) {
			return depense.get();
		}else {
		  throw new ResourceNotFoundExeption("Depense","ID",id) ;	
		}
	}

	@Override
	public Depense updateDepense(Depense depense, Long id) {
		long userId =depense.getUser().getId();
		Optional<User> user =userRepository.findById(userId);
		System.out.println(user.get());
		if (user.isEmpty()) {
			throw new ResourceNotFoundExeption("userId","ID",userId) ;
		}
		    Depense existingDepense =depenseRepositry.findById(id).orElseThrow(
				() -> new ResourceNotFoundExeption("Depense","Id",id)	);
		    existingDepense.setNom(depense.getNom());
		    existingDepense.setDat_ret(depense.getDat_ret());
		    existingDepense.setMontant(depense.getMontant());
		    existingDepense.setDescription(depense.getDescription());
		    existingDepense.setType(depense.getType());
		    existingDepense.setCategory(depense.getCategory());
		    existingDepense.setCompte(depense.getCompte());
			existingDepense.setUser(user.get());
			depenseRepositry.save(existingDepense);
			return existingDepense ;
	}

	@Override
	public void deleteDepense(Long id) {
		depenseRepositry.findById(id).orElseThrow(
				()-> new ResourceNotFoundExeption("Depense","Id",id)) ;
			depenseRepositry.deleteById(id);		
	}

}
