package com.champ.sample;

import java.io.File;

import com.champ.sample.graph.query.FooFetcher;
import com.champ.sample.graph.query.LinkFetcher;
import com.champ.sample.repository.LinkRepository;

import graphql.ExecutionResult;
import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;

public class GraphLink {

	public static void main(String[] args) {

		LinkRepository linkRepository = new LinkRepository();

		SchemaParser schemaParser = new SchemaParser();
		File schema = new File("src/main/resources/sample.graphqls");
		TypeDefinitionRegistry typeDefinitionRegistry = schemaParser.parse(schema);

		RuntimeWiring runtimeWiring = RuntimeWiring.newRuntimeWiring() //
				.type("Query", typeWiring -> typeWiring.dataFetcher("allLinks", new LinkFetcher(linkRepository))
						.dataFetcher("foo", new FooFetcher()) //
				) //
				.type("Link", typeWiring -> typeWiring.dataFetcher("foo", new FooFetcher())) //
				.build();

		SchemaGenerator schemaGenerator = new SchemaGenerator();
		GraphQLSchema graphQLSchema = schemaGenerator.makeExecutableSchema(typeDefinitionRegistry, runtimeWiring);

		GraphQL build = GraphQL.newGraphQL(graphQLSchema).build();
		ExecutionResult executionResult //
				= build.execute("{allLinks{url description}}");
		// = build.execute("{allLinks{url foo{name}}}");
		// = build.execute("{foo{name}}");

		System.out.println(executionResult.getData().toString());

		System.out.println(">>>");
	}

}
