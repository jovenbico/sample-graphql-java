package com.champ.sample.graph.query;

import com.champ.sample.domain.Foo;

import graphql.TypeResolutionEnvironment;
import graphql.schema.GraphQLObjectType;
import graphql.schema.TypeResolver;

public class FooResolver implements TypeResolver {

	@Override
	public GraphQLObjectType getType(TypeResolutionEnvironment env) {
		Object javaObject = env.getObject();

		if (javaObject instanceof Foo) {
			return (GraphQLObjectType) env.getSchema().getType("Foo");
		}
		return null;

	}

}
