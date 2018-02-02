package com.champ.sample.domain;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class Flight extends BaseDomain {

	private static final long serialVersionUID = -4420058112985016221L;

	private Long id;
	private String number;

	public Flight() {
		this(null);
	}

	public Flight(String number) {
		this.number = number;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public Long getId() {
		return id;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder(17, 37)//
				.append(id)//
				.append(number)//
				.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		Flight rhs = (Flight) obj;
		return new EqualsBuilder().appendSuper(super.equals(obj))//
				.append(id, rhs.id)//
				.append(number, rhs.number)//
				.isEquals();
	}

}
