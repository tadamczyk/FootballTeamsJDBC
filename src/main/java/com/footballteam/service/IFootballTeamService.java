package com.footballteam.service;

import java.util.List;
import com.footballteam.domain.FootballTeam;

public interface IFootballTeamService {
  public int addFootballTeam(FootballTeam footballTeam);

  public List<FootballTeam> getAllFootballTeams();

  public int updateFootballTeamName(FootballTeam footballTeam, String name);

  public int updateFootballTeamYearOfEstablished(FootballTeam footballTeam, int yearOfEstablished);

  public int updateFootballTeamMarketValue(FootballTeam footballTeam, double marketValue);

  public int removeAllFootballTeams();

  public int removeFootballTeamByName(String name);

  public FootballTeam findByName(String name);

  public FootballTeam findByYearOfEstablished(int yearOfEstablished);

  public FootballTeam findByMarketValue(double marketValue);

}
