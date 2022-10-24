package com.springboot.second.app.service;
import java.util.List;
import com.springboot.second.app.model.Revenue;
public interface RevenueService {
	Revenue saveRevenue(Revenue revenue);
	List<Revenue>getALLRevenues(Long user_id);
	Revenue getRevenueByID(Long id);
	Revenue updateRevenue(Revenue revenue,Long id );
    void deleteRevenue(Long id);
}
