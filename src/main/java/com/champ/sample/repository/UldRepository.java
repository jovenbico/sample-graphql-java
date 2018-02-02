package com.champ.sample.repository;

import org.springframework.stereotype.Repository;

import com.champ.sample.domain.Uld;

@Repository
public class UldRepository extends InMemoryRepository<Uld, Long> {

	@Override
	public void updateIfExist(Uld original, Uld updated) {
		original.setNumber(updated.getNumber());
	}

}
