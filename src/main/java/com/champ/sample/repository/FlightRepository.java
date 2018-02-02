package com.champ.sample.repository;

import org.springframework.stereotype.Repository;

import com.champ.sample.domain.Flight;

@Repository
public class FlightRepository extends InMemoryRepository<Flight, Long> {

	@Override
	public void updateIfExist(Flight original, Flight updated) {
		original.setNumber(updated.getNumber());
	}

}
