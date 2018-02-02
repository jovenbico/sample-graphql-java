package com.champ.sample.domain;

public class Uld extends BaseDomain {

	private static final long serialVersionUID = -3470832437248221476L;

	private Long id;
	private String number;

	private Flight flight;

	public Uld() {
		this(null, null);
	}

	public Uld(Flight flight, String number) {
		this.flight = flight;
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

	public Flight getFlight() {
		return flight;
	}

	public void setFlight(Flight flight) {
		this.flight = flight;
	}

}
