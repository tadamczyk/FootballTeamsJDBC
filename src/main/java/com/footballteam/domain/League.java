package com.footballteam.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

@Entity
@NamedQuery(name = "league.all", query = "SELECT l FROM League l")
public class League {
  private Long id;
  private String name, country;
  private int yearOfEstablished;
  private List<FootballTeam> footballTeams = new ArrayList<FootballTeam>();

  public League() {
    super();
  }

  public League(String name, String country, int numberOfTeams) {
    super();
    this.name = name;
    this.country = country;
    this.yearOfEstablished = numberOfTeams;
  }

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  @Column(unique = true)
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  public int getYearOfEstablished() {
    return yearOfEstablished;
  }

  public void setYearOfEstablished(int yearOfEstablished) {
    this.yearOfEstablished = yearOfEstablished;
  }

  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  public List<FootballTeam> getFootballTeams() {
    return footballTeams;
  }

  public void setFootballTeams(List<FootballTeam> footballTeams) {
    this.footballTeams = footballTeams;
  }

}
