package com.hoteis.apirest.repository;


import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.hoteis.apirest.models.Hotel;

public interface HotelRepository extends JpaRepository<Hotel, Long> {
	Hotel findById(long id);
	
	List<Hotel> findAllByOrderByPriceAsc();
	
	@Query("select h from Hotel h where LOWER(h.title) like %:title% and h.startDateAvailable <= :start and h.endDateAvailable >= :end")
	List<Hotel> findByAutocomplete(@Param("title") String title, @Param("start") Date startDateAvailable, @Param("end") Date endDateAvailable);
	
	List<Hotel> findByTitleContainingIgnoreCase(String title);

}
