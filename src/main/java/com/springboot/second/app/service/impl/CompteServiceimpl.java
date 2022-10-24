package com.springboot.second.app.service.impl;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.springboot.second.app.exeption.ResourceNotFoundExeption;
import com.springboot.second.app.model.Compte;
import com.springboot.second.app.model.User;
import com.springboot.second.app.repository.CompteRepository;
import com.springboot.second.app.repository.UserRepository;
import com.springboot.second.app.service.CompteService;
@Service
public class CompteServiceimpl implements CompteService{

	private CompteRepository compteRepository ;
	private UserRepository userRepository;
	@Autowired
	public CompteServiceimpl(CompteRepository compteRepository,UserRepository userRepository) {
		super();
		this.compteRepository = compteRepository;
		this.userRepository=userRepository;
	}
	@Override
	@Transactional
	public Compte saveCompte(Compte compte) {
		long userId =compte.getUser().getId();
		Optional<User> user =userRepository.findById(userId);
		System.out.println(user.get());
		if (user.isEmpty()) {
			throw new ResourceNotFoundExeption("userId","ID",userId) ;
		}		
		compte.setUser(user.get());
		return compteRepository.save(compte);
	}

	@Override
	public List<Compte>getALLComptes(Long user_id ) {
		return compteRepository.getComptes(user_id);
	}
	@Override
	public Compte getCompteByID(Long id) {
		@SuppressWarnings("deprecation")
		Optional<Compte>compte=Optional.ofNullable(compteRepository.getById(id));
		if(compte.isPresent()) {
			return compte.get();
		}else {
		  throw new ResourceNotFoundExeption("Compte","ID",id) ;	
		}
	}

	@Override
	public Compte updateCompte(Compte compte, Long id) {
		Compte existingCompte =compteRepository.findById(id).orElseThrow(
				() -> new ResourceNotFoundExeption("Compte","Id",id)	);
		    existingCompte.setBalance(compte.getBalance());
		    existingCompte.setCurrency(compte.getCurrency());
		    existingCompte.setName(compte.getName());
		    existingCompte.setUser(compte.getUser());
		    compteRepository.save(existingCompte);
			return existingCompte ;
	}

	@Override
	public void deleteCompte(Long id) {
		compteRepository.findById(id).orElseThrow(
				()-> new ResourceNotFoundExeption("Compte","Id",id)) ;
		compteRepository.deleteById(id);
		
	}

}
