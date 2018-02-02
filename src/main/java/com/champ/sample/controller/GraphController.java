package com.champ.sample.controller;

import java.io.File;

import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;

public abstract class GraphController {

	protected final GraphQLSchema GRAPHQL_SCHEMA;

	public GraphController(String graphqlSchemaFile) {

		SchemaParser schemaParser = new SchemaParser();
		File schema = new File(graphqlSchemaFile);
		TypeDefinitionRegistry typeDefinitionRegistry = schemaParser.parse(schema);

		RuntimeWiring runtimeWiring = RuntimeWiring.newRuntimeWiring() //
				// .type("FlightQuery",
				// typeWiring -> typeWiring.dataFetcher("allFlights", new
				// AllFlightFetcher(flightRepository))) //
				.build();

		SchemaGenerator schemaGenerator = new SchemaGenerator();
		GRAPHQL_SCHEMA = schemaGenerator.makeExecutableSchema(//
				typeDefinitionRegistry, runtimeWiring//
		);

	}

}
