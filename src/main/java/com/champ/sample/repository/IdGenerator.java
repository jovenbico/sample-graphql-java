package com.champ.sample.repository;

import java.util.concurrent.atomic.AtomicLong;

public class IdGenerator {

	private final AtomicLong ID = new AtomicLong(100l);

	public static final IdGenerator ME = new IdGenerator();

	private IdGenerator() {
	}

	public Long nextId() {
		return ID.incrementAndGet();
	}

}
