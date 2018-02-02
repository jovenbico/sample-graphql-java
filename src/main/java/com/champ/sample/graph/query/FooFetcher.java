package com.champ.sample.graph.query;

import com.champ.sample.domain.Foo;
import com.champ.sample.domain.Link;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;

public class FooFetcher implements DataFetcher<Foo> {

	@Override
	public Foo get(DataFetchingEnvironment environment) {

		Link link = environment.getSource();
		System.out.println(">> it happens >> " + link.getUrl());

		return new Foo("foo-name");
	}

}
