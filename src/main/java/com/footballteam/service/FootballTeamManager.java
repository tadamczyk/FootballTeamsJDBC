package com.footballteam.service;

import java.util.List;

import com.footballteam.domain.FootballTeam;

public interface FootballTeamManager {

  void addFootballTeam(FootballTeam footballTeam);

  List<FootballTeam> getAllFootballTeams();

  void updateFootballTeam(FootballTeam footballTeam);

  void removeFootballTeam(FootballTeam footballTeam);

  FootballTeam findById(Long id);

  FootballTeam findByName(String name);

}
