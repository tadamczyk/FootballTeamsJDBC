package com.tomasz.service;

import java.util.List;
import com.tomasz.domain.FootballTeam;

public interface IFootballTeamService {
  public int addFootballTeam(FootballTeam footballTeam);

  public void removeAllFootballTeams();

  public List<FootballTeam> getAllFootballTeams();
}
