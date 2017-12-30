package com.footballteam.domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;

@Entity
@NamedQuery(name = "player.all", query = "SELECT p FROM Player p")
public class Player {
  private Long id;
  private String firstname, lastname;
  private int yearOfBirth;
  private FootballTeam footballTeam;

  public Player() {
    super();
  }

  public Player(String firstname, String lastname, int yearOfBirth, FootballTeam footballTeam) {
    super();
    this.firstname = firstname;
    this.lastname = lastname;
    this.yearOfBirth = yearOfBirth;
    this.footballTeam = footballTeam;
  }

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getFirstname() {
    return firstname;
  }

  public void setFirstname(String firstname) {
    this.firstname = firstname;
  }

  public String getLastname() {
    return lastname;
  }

  public void setLastname(String lastname) {
    this.lastname = lastname;
  }

  public int getYearOfBirth() {
    return yearOfBirth;
  }

  public void setYearOfBirth(int yearOfBirth) {
    this.yearOfBirth = yearOfBirth;
  }

  @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  public FootballTeam getFootballTeam() {
    return footballTeam;
  }

  public void setFootballTeam(FootballTeam footballTeam) {
    this.footballTeam = footballTeam;
  }

}
