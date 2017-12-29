package com.footballteam.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;

@Entity
@NamedQuery(name = "footballTeam.all", query = "SELECT f FROM FootballTeam f")
public class FootballTeam {

  public FootballTeam() {
    super();
  }

  public FootballTeam(String name, int yearOfEstablished, double marketValue) {
    super();
    this.name = name;
    this.yearOfEstablished = yearOfEstablished;
    this.marketValue = marketValue;
  }

  private Long id;
  private String name;
  private int yearOfEstablished;
  private double marketValue;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
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