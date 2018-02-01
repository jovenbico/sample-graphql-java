package com.champ.sample.graph.query;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;

public class DefaultDataFetcher implements DataFetcher<String> {

	@Override
	public String get(DataFetchingEnvironment environment) {
		System.out.println(">>> xyz");
		return null;
	}

}
