package com.champ.sample.graph.query;

import com.champ.sample.domain.Flight;
import com.champ.sample.repository.FlightRepository;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;

public class FlightFetcher implements DataFetcher<Flight> {

	private final FlightRepository flightRepository;

	public FlightFetcher(FlightRepository flightRepository) {
		this.flightRepository = flightRepository;
	}

	@Override
	public Flight get(DataFetchingEnvironment environment) {

		String flightNumber = environment.getArgument("flightNumber");

		return flightRepository.readAll().stream()//
				.filter(flight -> flightNumber.equals(flight.getNumber()))//
				.findFirst().get();
	}

}
