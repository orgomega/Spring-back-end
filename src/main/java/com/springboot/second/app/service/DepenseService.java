package com.springboot.second.app.service;
import java.util.List;
import com.springboot.second.app.model.Depense;
public interface DepenseService {
	Depense saveDepense(Depense depense);
	List<Depense>getALLDepenses(Long user_id);
	Depense getDepenseByID(Long id);
	Depense updateDepense(Depense depense,Long id );
    void deleteDepense(Long id);
}
