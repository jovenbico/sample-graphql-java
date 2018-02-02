package com.champ.sample.graph.query;

import java.util.List;

import com.champ.sample.domain.Flight;
import com.champ.sample.repository.FlightRepository;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;

public class AllFlightFetcher implements DataFetcher<List<Flight>> {

	private FlightRepository flightRepository;

	public AllFlightFetcher(FlightRepository flightRepository) {
		this.flightRepository = flightRepository;
	}

	@Override
	public List<Flight> get(DataFetchingEnvironment environment) {
		return flightRepository.readAll();
	}

}
