package com.springboot.second.app.service.impl;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.springboot.second.app.exeption.ResourceNotFoundExeption;
import com.springboot.second.app.model.Carte;
import com.springboot.second.app.model.Compte;
import com.springboot.second.app.model.User;
import com.springboot.second.app.repository.CarteRepository;
import com.springboot.second.app.repository.CompteRepository;
import com.springboot.second.app.repository.UserRepository;
import com.springboot.second.app.service.CarteService;
@Service
public class CarteServiceimpl implements CarteService {
	private CarteRepository carteRepositry;
	private UserRepository userRepository;
	private CompteRepository compteRepository;
	 @Autowired
	 public CarteServiceimpl(CarteRepository carteRepositry,UserRepository userRepository,CompteRepository compteRepository) {
			super();
			this.carteRepositry = carteRepositry;
			this.userRepository=userRepository;
			this.compteRepository=compteRepository;
		}
	    @Override
		@Transactional
		public Carte saveCarte(Carte carte) {
	    	long compteId=carte.getCompte().getId();
			long userId =carte.getUser().getId();
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
			carte.setCompte(compte.get());
			carte.setUser(user.get());
			return carteRepositry.save(carte);
		}
	    @Override
		public List<Carte> getALLCartes(Long user_id ) {
			return carteRepositry.getCartes(user_id);
		}
	    @SuppressWarnings("deprecation")
		@Override
		public Carte getCarteByID(Long id) {
			Optional<Carte>carte=Optional.ofNullable(carteRepositry.getById(id));
			if(carte.isPresent()) {
				return carte.get();
			}else {
			  throw new ResourceNotFoundExeption("Carte","ID",id) ;	
			}
		}
	    @Override
		public Carte updateCarte(Carte carte, Long id) {
			long userId =carte.getUser().getId();
			Optional<User> user =userRepository.findById(userId);
			System.out.println(user.get());
			if (user.isEmpty()) {
				throw new ResourceNotFoundExeption("userId","ID",userId) ;
			}
			    Carte existingCarte =carteRepositry.findById(id).orElseThrow(
					() -> new ResourceNotFoundExeption("Depense","Id",id)	);
			    existingCarte.setInterest_rate(carte.getInterest_rate());
			    existingCarte.setLimit(carte.getLimit());
			    existingCarte.setName(carte.getName());
			    existingCarte.setUser(user.get());
				carteRepositry.save(existingCarte);
				return existingCarte ;
		}
	    @Override
		public void deleteCarte(Long id) {
	    	carteRepositry.findById(id).orElseThrow(
					()-> new ResourceNotFoundExeption("Depense","Id",id)) ;
	    	carteRepositry.deleteById(id);		
		}
}
