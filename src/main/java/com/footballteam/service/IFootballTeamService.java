package com.footballteam.service;

import java.util.List;

import com.footballteam.domain.FootballTeam;

public interface IFootballTeamService {
  public int addFootballTeam(FootballTeam footballTeam);

  public List<FootballTeam> getAllFootballTeams();

  public void updateFootballTeamName(FootballTeam footballTeam, String name);

  public void updateFootballTeamYearOfEstablished(FootballTeam footballTeam, int yearOfEstablished);

  public void updateFootballTeamMarketValue(FootballTeam footballTeam, double marketValue);

  public int removeAllFootballTeams();

  public int removeFootballTeamByName(String name);

  public FootballTeam findByName(String name);

  public FootballTeam findByYearOfEstablished(int yearOfEstablished);

  public FootballTeam findByMarketValue(double marketValue);

}
