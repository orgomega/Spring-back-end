package com.springboot.second.app.service.impl;

import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.second.app.exeption.ResourceNotFoundExeption;
import com.springboot.second.app.model.Compte;
import com.springboot.second.app.model.Dette;
import com.springboot.second.app.model.User;
import com.springboot.second.app.repository.CompteRepository;
import com.springboot.second.app.repository.DetteRepository;
import com.springboot.second.app.repository.UserRepository;
import com.springboot.second.app.service.DetteService;
@Service
public class DetteServiceimpl implements DetteService{
	private DetteRepository detteRepositry;
	private UserRepository userRepository;
	private CompteRepository compteRepository;
    @Autowired
	public DetteServiceimpl(DetteRepository detteRepositry,UserRepository userRepository,CompteRepository compteRepository) {
		super();
		this.detteRepositry = detteRepositry;
		this.userRepository=userRepository;
		this.compteRepository=compteRepository;
	}
    @Override
	@Transactional
	public Dette saveDette(Dette dette) {
    	long compteId=dette.getCompte().getId();
		long userId =dette.getUser().getId();
		Optional<Compte> compte=compteRepository.findById(compteId);
		Optional<User> user =userRepository.findById(userId);
		System.out.println(compte.get());
		if (user.isEmpty()) {
			throw new ResourceNotFoundExeption("userId","ID",userId) ;
		}		
		if (compte.isEmpty()) {
			throw new ResourceNotFoundExeption("compteId","ID",compteId) ;
		}
		dette.setCompte(compte.get());
		dette.setUser(user.get());
		return detteRepositry.save(dette);
	}
    @Override
	public List<Dette> getALLDettes(Long user_id ) {
		return detteRepositry.getDettes(user_id);
	}
    @SuppressWarnings("deprecation")
	@Override
	public Dette getDetteByID(Long id) {
		Optional<Dette>dette=Optional.ofNullable(detteRepositry.getById(id));
		if(dette.isPresent()) {
			return dette.get();
		}else {
		  throw new ResourceNotFoundExeption("Dette","ID",id) ;	
		}
	}
    @Override
	public Dette updateDette(Dette dette, Long id) {
		long userId =dette.getUser().getId();
		Optional<User> user =userRepository.findById(userId);
		System.out.println(user.get());
		if (user.isEmpty()) {
			throw new ResourceNotFoundExeption("userId","ID",userId) ;
		}
		    Dette existingDette =detteRepositry.findById(id).orElseThrow(
				() -> new ResourceNotFoundExeption("Dette","Id",id)	);
		    existingDette.setPay_date(dette.getPay_date());
		    existingDette.setDate(dette.getDate());
		    existingDette.setCompte(dette.getCompte());
		    existingDette.setFrom(dette.getFrom());
		    existingDette.setName(dette.getName());
		    existingDette.setValue(dette.getValue());
		    existingDette.setUser(user.get());
		    detteRepositry.save(existingDette);
			return existingDette ;
	}
    @Override
	public void deleteDette(Long id) {
    	detteRepositry.findById(id).orElseThrow(
				()-> new ResourceNotFoundExeption("Dette","Id",id)) ;
    	detteRepositry.deleteById(id);		
	}
}
