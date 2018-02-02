package com.champ.sample.repository;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import com.champ.sample.domain.BaseDomain;

public abstract class InMemoryRepository<M extends BaseDomain, ID extends Serializable> {

	private List<M> heap = Collections.synchronizedList(new ArrayList<>());

	public void create(M model) {
		model.setId(IdGenerator.ME.nextId());
		heap.add(model);
	}

	public Optional<M> read(Long id) {
		return heap.stream().filter(model -> model.getId().equals(id)).findFirst();
	}

	public List<M> readAll() {
		return new ArrayList<>(heap);
	}

	public boolean update(Long id, M updated) {
		Optional<M> original = read(id);
		original.ifPresent(model -> updateIfExist(model, updated));

		return original.isPresent();
	}

	public boolean delete(Long id) {
		return heap.removeIf(model -> model.getId().equals(id));
	}

	public abstract void updateIfExist(M original, M updated);

}
