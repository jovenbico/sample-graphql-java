package com.champ.sample.domain;

public class Link {
	private final String url;
	private final String description;

	private Foo foo;

	public Link(String url, String description) {
		this.url = url;
		this.description = description;
	}

	public String getUrl() {
		return url;
	}

	public String getDescription() {
		return description;
	}

	public Foo getFoo() {
		return foo;
	}

	public void setFoo(Foo foo) {
		this.foo = foo;
	}

}
