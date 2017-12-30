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
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

@Entity
@NamedQueries({ @NamedQuery(name = "footballTeam.all", query = "SELECT f FROM FootballTeam f"),
    @NamedQuery(name = "footballTeam.byName", query = "SELECT f FROM FootballTeam f WHERE f.name = :name") })
public class FootballTeam {
  private Long id;
  private String name;
  private int yearOfEstablished;
  private double marketValue;
  private League league;
  private List<Player> players = new ArrayList<Player>();

  public FootballTeam() {
    super();
  }

  public FootballTeam(String name, int yearOfEstablished, double marketValue, League league) {
    super();
    this.name = name;
    this.yearOfEstablished = yearOfEstablished;
    this.marketValue = marketValue;
    this.league = league;
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

  @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  public League getLeague() {
    return league;
  }

  public void setLeague(League league) {
    this.league = league;
  }

  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  public List<Player> getPlayers() {
    return players;
  }

  public void setPlayers(List<Player> players) {
    this.players = players;
  }

}