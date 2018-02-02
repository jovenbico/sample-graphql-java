package com.champ.sample.domain;

import java.io.Serializable;

public abstract class BaseDomain implements Serializable {

	private static final long serialVersionUID = 3589305979454525980L;

	public abstract void setId(Long id);

	public abstract Long getId();
}
