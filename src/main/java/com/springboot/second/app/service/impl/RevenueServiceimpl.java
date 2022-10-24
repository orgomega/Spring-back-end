package com.springboot.second.app.service.impl;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.springboot.second.app.exeption.ResourceNotFoundExeption;
import com.springboot.second.app.model.Compte;
import com.springboot.second.app.model.Revenue;
import com.springboot.second.app.model.User;
import com.springboot.second.app.repository.CompteRepository;
import com.springboot.second.app.repository.RevenueRepository;
import com.springboot.second.app.repository.UserRepository;
import com.springboot.second.app.service.RevenueService;
@Service
public class RevenueServiceimpl implements RevenueService{

	private RevenueRepository revenueRepositry;
	private UserRepository userRepository;
	private CompteRepository compteRepository;
    @Autowired
    public RevenueServiceimpl(RevenueRepository revenueRepositry,UserRepository userRepository,CompteRepository compteRepository) {
		super();
		this.revenueRepositry = revenueRepositry;
		this.userRepository = userRepository ;
		this.compteRepository=compteRepository;
	}
	@Override
	public Revenue saveRevenue(Revenue revenue) {
		long compteId=revenue.getCompte().getId();
		long userId =revenue.getUser().getId();
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
		revenue.setCompte(compte.get());		
		revenue.setUser(user.get());
		return revenueRepositry.save(revenue);
	}

	@Override
	public List<Revenue> getALLRevenues(Long user_id ) {
		return revenueRepositry.getRevenues(user_id);
	}

	@SuppressWarnings("deprecation")
	@Override
	public Revenue getRevenueByID(Long id) {
		Optional<Revenue>revenue=Optional.ofNullable(revenueRepositry.getById(id));
		if(revenue.isPresent()) {
			return revenue.get();
		}else {
		  throw new ResourceNotFoundExeption("revenue","ID",id) ;	
		}		
	}

	@Override
	public Revenue updateRevenue(Revenue revenue, Long id) {
		long userId =revenue.getUser().getId();
		Optional<User> user =userRepository.findById(userId);
		System.out.println(user.get());
		Revenue existingRevenue =revenueRepositry.findById(id).orElseThrow(
				() -> new ResourceNotFoundExeption("revenue","Id",id)	);
		    existingRevenue.setNom(revenue.getNom());
		    existingRevenue.setDat_ver(revenue.getDat_ver());
		    existingRevenue.setMontant(revenue.getMontant());
		    existingRevenue.setDescription(revenue.getDescription());
		    existingRevenue.setCategory(revenue.getCategory());
		    existingRevenue.setType(revenue.getType());
		    existingRevenue.setUser(user.get());
			revenueRepositry.save(existingRevenue);
			return existingRevenue ;
	}
	@Override
	public void deleteRevenue(Long id) {
		revenueRepositry.findById(id).orElseThrow(
				()-> new ResourceNotFoundExeption("revenue","Id",id)) ;
			revenueRepositry.deleteById(id);			
	}

}
