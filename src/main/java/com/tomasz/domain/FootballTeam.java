package com.tomasz.domain;

public class FootballTeam {
	private long id;
	private String name;
	private int yearOfEstablished;
	private double marketValue;

	public FootballTeam() {
	}

	public FootballTeam(String name, int yearOfEstablished, double marketValue) {
		super();
		this.name = name;
		this.yearOfEstablished = yearOfEstablished;
		this.marketValue = marketValue;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getYearOfEstablished() {
		return yearOfEstablished;
	}

	public void setYearOfEstablished(int yearOfEstablished) {
		this.yearOfEstablished = yearOfEstablished;
	}

	public double getMarketValue() {
		return marketValue;
	}

	public void setMarketValue(double marketValue) {
		this.marketValue = marketValue;
	}

}
