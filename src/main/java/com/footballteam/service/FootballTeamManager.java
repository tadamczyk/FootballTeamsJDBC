package com.footballteam.service;

import java.util.List;

import com.footballteam.domain.FootballTeam;
import com.footballteam.domain.League;
import com.footballteam.domain.Player;

public interface FootballTeamManager {

  void addFootballTeam(FootballTeam footballTeam);

  List<FootballTeam> getAllFootballTeams();

  void updateFootballTeam(FootballTeam footballTeam);

  void removeFootballTeam(FootballTeam footballTeam);

  FootballTeam findById(Long id);

  FootballTeam findByName(String name);

  void addPlayer(Player player);

  List<Player> getAllPlayers();

  void updatePlayer(Player player);

  void removePlayer(Player player);

  Player findPlayerById(Long id);

  void addLeague(League league);

  List<League> getAllLeagues();

  void updateLeague(League league);

  void removeLeague(League league);

  League findLeagueById(Long id);

}
