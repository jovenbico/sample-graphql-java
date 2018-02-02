package com.champ.sample.graph.query;

import java.util.List;

import com.champ.sample.domain.Link;
import com.champ.sample.repository.LinkRepository;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;

public class LinkFetcher implements DataFetcher<List<Link>> {

	private LinkRepository linkRepository;

	public LinkFetcher(LinkRepository linkRepository) {
		this.linkRepository = linkRepository;
	}

	@Override
	public List<Link> get(DataFetchingEnvironment environment) {
		return linkRepository.getAllLinks();
	}

}
