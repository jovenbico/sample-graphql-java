package com.champ.sample.graph.query;

import java.util.List;
import java.util.stream.Collectors;

import com.champ.sample.domain.Flight;
import com.champ.sample.domain.Uld;
import com.champ.sample.repository.UldRepository;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;

public class UldFetcher implements DataFetcher<List<Uld>> {

	private UldRepository uldRepository;

	public UldFetcher(UldRepository uldRepository) {
		this.uldRepository = uldRepository;
	}

	@Override
	public List<Uld> get(DataFetchingEnvironment environment) {
		System.out.println(">>> ulds");

		Flight flight = environment.getSource();

		List<Uld> ulds = uldRepository.readAll().stream()//
				.filter(uld -> flight.equals(uld.getFlight()))//
				.collect(Collectors.toList());

		return ulds;
	}

}
