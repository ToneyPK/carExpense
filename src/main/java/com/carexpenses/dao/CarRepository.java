package com.carexpenses.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.carexpenses.entity.Car;



public interface CarRepository extends JpaRepository<Car, Integer> {

	@Query("SELECT c FROM Car c WHERE c.carOwner = :user " )
    List<Car> listCarsByOwner(@Param("user") String user);
	
}
