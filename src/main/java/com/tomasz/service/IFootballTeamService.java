package com.tomasz.service;

import java.util.List;
import com.tomasz.domain.FootballTeam;

public interface IFootballTeamService {
  public int addFootballTeam(FootballTeam footballTeam);

  public List<FootballTeam> getAllFootballTeams();

  public void updateFootballTeamName(FootballTeam footballTeam, String name);

  public void updateFootballTeamYearOfEstablished(FootballTeam footballTeam, int yearOfEstablished);

  public void updateFootballTeamMarketValue(FootballTeam footballTeam, double marketValue);

  public void removeAllFootballTeams();

  public void removeFootballTeamById(long id);

  public void removeFootballTeamByName(String name);

  public FootballTeam findById(long id);

  public FootballTeam findByName(String name);

  public FootballTeam findByYearOfEstablished(int yearOfEstablished);

  public FootballTeam findByMarketValue(double marketValue);

}
