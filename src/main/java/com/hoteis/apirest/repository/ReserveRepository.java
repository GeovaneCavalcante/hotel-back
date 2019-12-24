package com.hoteis.apirest.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.hoteis.apirest.models.Reserve;


public interface ReserveRepository  extends JpaRepository<Reserve, Long>{
	Reserve findById(long id);
	
	@Query("Select r FROM Reserve r where r.user.id = :userId")
	List<Reserve> findAllByCurrentUser(@Param("userId") long userId);
}
