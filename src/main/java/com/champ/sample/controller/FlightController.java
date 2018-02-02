package com.champ.sample.controller;

import java.io.File;
import java.util.LinkedHashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.champ.sample.domain.Flight;
import com.champ.sample.graph.query.AllFlightFetcher;
import com.champ.sample.graph.query.FlightFetcher;
import com.champ.sample.graph.query.UldFetcher;
import com.champ.sample.repository.FlightRepository;
import com.champ.sample.repository.UldRepository;

import graphql.ExecutionResult;
import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;

@RestController
@RequestMapping(value = "/flights", //
		consumes = { MediaType.APPLICATION_JSON_VALUE }, //
		produces = { MediaType.APPLICATION_JSON_VALUE })
public class FlightController {

	private final Logger LOG = LoggerFactory.getLogger(getClass());

	private final GraphQLSchema GRAPHQL_SCHEMA;

	private FlightRepository flightRepository;
	private UldRepository uldRepository;

	@Autowired
	public FlightController(//
			FlightRepository flightRepository, //
			UldRepository uldRepository//
	) {
		this.flightRepository = flightRepository;
		this.uldRepository = uldRepository;

		SchemaParser schemaParser = new SchemaParser();
		File schema = new File("src/main/resources/flight.graphqls");
		TypeDefinitionRegistry typeDefinitionRegistry = schemaParser.parse(schema);

		RuntimeWiring runtimeWiring = RuntimeWiring.newRuntimeWiring() //
				.type("FlightQuery", typeWiring -> typeWiring//
						.dataFetcher("allFlights", new AllFlightFetcher(flightRepository))//
						.dataFetcher("flightByNumber", new FlightFetcher(flightRepository))//
				) //
				.type("Flight", typeWiring -> typeWiring.dataFetcher("ulds", new UldFetcher(uldRepository)))//
				.build();

		SchemaGenerator schemaGenerator = new SchemaGenerator();
		GRAPHQL_SCHEMA = schemaGenerator.makeExecutableSchema(//
				typeDefinitionRegistry, runtimeWiring//
		);

	}

	@GetMapping
	@ResponseBody
	public ResponseEntity<List<Flight>> flightService() {
		return ResponseEntity.ok(flightRepository.readAll());
	}

	@ResponseBody
	@GetMapping("/q")
	public ResponseEntity<LinkedHashMap<String, Object>> flightGraph(//
			@RequestParam String query//
	) {
		LOG.trace(">>> flight query request >>> {}", query);

		GraphQL build = GraphQL.newGraphQL(GRAPHQL_SCHEMA).build();
		ExecutionResult executionResult = build.execute(query);
		LOG.trace(">>> flight query graph");

		LinkedHashMap<String, Object> resource = executionResult.getData();
		return ResponseEntity.ok(resource);
	}

}
