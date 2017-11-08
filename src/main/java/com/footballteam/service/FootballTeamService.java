package com.footballteam.service;

import java.util.List;
import com.footballteam.domain.FootballTeam;

public interface FootballTeamService {
  public int addFootballTeam(FootballTeam footballTeam);

  public int addAllFootballTeamsFromList(List<FootballTeam> footballTeams);

  public List<FootballTeam> getAllFootballTeams();

  public int updateFootballTeam(FootballTeam footballTeam, String name, int yearOfEstablished, double marketValue);

  public int updateFootballTeamName(FootballTeam footballTeam, String name);

  public int updateFootballTeamYearOfEstablished(FootballTeam footballTeam, int yearOfEstablished);

  public int updateFootballTeamMarketValue(FootballTeam footballTeam, double marketValue);

  public int removeAllFootballTeams();

  public int removeFootballTeamById(long id);

  public int removeFootballTeamByName(String name);

  public FootballTeam findById(long id);

  public FootballTeam findByName(String name);

  public FootballTeam findByYearOfEstablished(int yearOfEstablished);

  public FootballTeam findByMarketValue(double marketValue);

}
