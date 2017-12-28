package com.footballteam.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@NamedQueries({ @NamedQuery(name = "footballTeam.all", query = "SELECT f FROM FootballTeam f"),
    @NamedQuery(name = "footballTeam.byId", query = "SELECT f FROM FootballTeam f WHERE f.id = :id"),
    @NamedQuery(name = "footballTeam.byName", query = "SELECT f FROM FootballTeam f WHERE f.name = :name") })
public class FootballTeam {

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