package com.champ.sample;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.champ.sample.domain.Flight;
import com.champ.sample.domain.Uld;
import com.champ.sample.repository.FlightRepository;
import com.champ.sample.repository.UldRepository;

@Component
public class ApplicationStartup implements ApplicationListener<ApplicationReadyEvent> {

	private Logger LOG = LoggerFactory.getLogger(getClass());

	@Autowired
	private FlightRepository flightRepository;

	@Autowired
	private UldRepository uldRepository;

	@Override
	public void onApplicationEvent(ApplicationReadyEvent event) {

		flightRepository.create(new Flight("F01"));
		flightRepository.create(new Flight("F02"));
		flightRepository.create(new Flight("F03"));

		flightRepository.readAll().stream().forEach(flight -> {
			uldRepository.create(new Uld(flight, "U01"));
			uldRepository.create(new Uld(flight, "U02"));
			uldRepository.create(new Uld(flight, "U03"));
		});

		LOG.info(">>> application is ready");
	}

}
