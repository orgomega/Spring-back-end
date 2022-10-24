package com.springboot.second.app.service;

import java.util.List;

import com.springboot.second.app.model.Dette;

public interface DetteService {
	Dette saveDette(Dette dette);
	List<Dette>getALLDettes(Long user_id);
	Dette getDetteByID(Long id);
	Dette updateDette(Dette dette,Long id );
    void deleteDette(Long id);
}
