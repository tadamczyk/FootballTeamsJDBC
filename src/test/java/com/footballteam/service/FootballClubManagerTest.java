package com.footballteam.service;

import static org.hamcrest.CoreMatchers.either;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.footballteam.domain.FootballTeam;
import com.footballteam.domain.League;
import com.footballteam.domain.Player;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/beans.xml" })
@TransactionConfiguration(transactionManager = "txManager", defaultRollback = false)
@Transactional
public class FootballClubManagerTest {

  private static final double _PRECISION = 0.01;

  private static final String LIV_NAME = "FC Liverpool";
  private static final int LIV_YOE = 1892;
  private static final double LIV_MV = 512000000.01;

  private static final String EVE_NAME = "FC Everton";
  private static final int EVE_YOE = 1878;
  private static final double EVE_MV = 364000000.50;

  private static final String BAR_NAME = "FC Barcelona";
  private static final int BAR_YOE = 1899;
  private static final int BAR_MV = 650000000;

  private static final String ENGLAND_NAME = "Premier League";
  private static final String ENGLAND_COUNTRY = "England";
  private static final int ENGLAND_YOE = 1992;

  private static final String SPAIN_NAME = "La Liga";
  private static final String SPAIN_COUNTRY = "Spain";
  private static final int SPAIN_YOE = 1929;

  private static final String ROONEY_FIRSTNAME = "Wayne";
  private static final String ROONEY_LASTNAME = "Rooney";
  private static final int ROONEY_YOB = 1985;

  private static final String DE_GEA_FIRSTNAME = "David";
  private static final String DE_GEA_LASTNAME = "De Gea";
  private static final int DE_GEA_YOB = 1990;

  @Autowired
  FootballClubManager manager;

  @Test
  public void shouldAddOneFootballTeam() {
    int counterBeforeAddOneFootballTeam = manager.getAllFootballTeams().size();
    FootballTeam footballTeam = new FootballTeam(LIV_NAME, LIV_YOE, LIV_MV, new League());
    manager.addFootballTeam(footballTeam);
    int footballTeamsCounter = manager.getAllFootballTeams().size();
    assertEquals(counterBeforeAddOneFootballTeam + 1, footballTeamsCounter);
    FootballTeam temporaryFootballTeam = manager.getAllFootballTeams().get(footballTeamsCounter - 1);
    manager.removeFootballTeam(temporaryFootballTeam);
  }

  @Test
  public void shouldGetAllFootballTeams() {
    int footballTeamsCounter = manager.getAllFootballTeams().size();
    assertThat(footballTeamsCounter, either(is(0)).or(is(1)));
  }

  @Test
  public void shouldUpdateAllFieldsFootballTeam() {
    FootballTeam footballTeam = new FootballTeam(BAR_NAME, BAR_YOE, BAR_MV, new League());
    manager.addFootballTeam(footballTeam);
    int footballTeamsCounter = manager.getAllFootballTeams().size();
    FootballTeam retrievedFootballTeam = manager.getAllFootballTeams().get(footballTeamsCounter - 1);
    retrievedFootballTeam.setName(EVE_NAME);
    retrievedFootballTeam.setYearOfEstablished(EVE_YOE);
    retrievedFootballTeam.setMarketValue(EVE_MV);
    retrievedFootballTeam.setLeague(new League(ENGLAND_NAME, ENGLAND_COUNTRY, ENGLAND_YOE));
    manager.updateFootballTeam(retrievedFootballTeam);
    retrievedFootballTeam = manager.getAllFootballTeams().get(footballTeamsCounter - 1);
    assertEquals(retrievedFootballTeam.getName(), EVE_NAME);
    assertEquals(retrievedFootballTeam.getYearOfEstablished(), EVE_YOE);
    assertEquals(retrievedFootballTeam.getMarketValue(), EVE_MV, _PRECISION);
    assertEquals(retrievedFootballTeam.getLeague().getName(), ENGLAND_NAME);
    FootballTeam temporaryFootballTeam = manager.getAllFootballTeams().get(footballTeamsCounter - 1);
    manager.removeFootballTeam(temporaryFootballTeam);
  }

  @Test
  public void shouldRemoveOneAddedFootballTeam() {
    FootballTeam footballTeam = new FootballTeam(BAR_NAME, BAR_YOE, BAR_MV, new League());
    manager.addFootballTeam(footballTeam);
    int counterBeforeRemoveOneFootballTeam = manager.getAllFootballTeams().size();
    FootballTeam retrievedFootballTeam = manager.getAllFootballTeams().get(counterBeforeRemoveOneFootballTeam - 1);
    manager.removeFootballTeam(retrievedFootballTeam);
    int footballTeamsCounter = manager.getAllFootballTeams().size();
    assertEquals(counterBeforeRemoveOneFootballTeam - 1, footballTeamsCounter);
  }

  @Test
  public void shouldFindCorrectlyFootballTeamById() {
    FootballTeam footballTeam = new FootballTeam(BAR_NAME, BAR_YOE, BAR_MV, new League());
    manager.addFootballTeam(footballTeam);
    long id = footballTeam.getId();
    FootballTeam retrievedFootballTeam = manager.findById(id);
    assertEquals(retrievedFootballTeam, footballTeam);
    manager.removeFootballTeam(retrievedFootballTeam);
  }

  @Test
  public void shouldFindCorrectlyFootballTeamByName() {
    FootballTeam footballTeam = new FootballTeam(BAR_NAME, BAR_YOE, BAR_MV, new League());
    manager.addFootballTeam(footballTeam);
    String name = footballTeam.getName();
    FootballTeam retrievedFootballTeam = manager.findByName(name);
    assertEquals(retrievedFootballTeam, footballTeam);
    manager.removeFootballTeam(retrievedFootballTeam);
  }

  @Test
  public void shouldAddOnePlayer() {
    int counterBeforeAddOnePlayer = manager.getAllPlayers().size();
    Player player = new Player(ROONEY_FIRSTNAME, ROONEY_LASTNAME, ROONEY_YOB, new FootballTeam());
    manager.addPlayer(player);
    int playersCounter = manager.getAllPlayers().size();
    assertEquals(counterBeforeAddOnePlayer + 1, playersCounter);
    Player temporaryPlayer = manager.getAllPlayers().get(playersCounter - 1);
    manager.removePlayer(temporaryPlayer);
  }

  @Test
  public void shouldGetAllPlayers() {
    int playersCounter = manager.getAllPlayers().size();
    assertThat(playersCounter, either(is(0)).or(is(1)));
  }

  @Test
  public void shouldUpdateAllFieldsPlayer() {
    Player player = new Player(ROONEY_FIRSTNAME, ROONEY_LASTNAME, ROONEY_YOB, new FootballTeam());
    manager.addPlayer(player);
    int playersCounter = manager.getAllPlayers().size();
    Player retrievedPlayer = manager.getAllPlayers().get(playersCounter - 1);
    retrievedPlayer.setFirstname(DE_GEA_FIRSTNAME);
    retrievedPlayer.setLastname(DE_GEA_LASTNAME);
    retrievedPlayer.setYearOfBirth(1990);
    retrievedPlayer.setFootballTeam(new FootballTeam(EVE_NAME, EVE_YOE, EVE_MV, new League()));
    manager.updatePlayer(retrievedPlayer);
    retrievedPlayer = manager.getAllPlayers().get(playersCounter - 1);
    assertEquals(retrievedPlayer.getFirstname(), DE_GEA_FIRSTNAME);
    assertEquals(retrievedPlayer.getLastname(), DE_GEA_LASTNAME);
    assertEquals(retrievedPlayer.getYearOfBirth(), DE_GEA_YOB);
    assertEquals(retrievedPlayer.getFootballTeam().getName(), EVE_NAME);
    Player temporaryPlayer = manager.getAllPlayers().get(playersCounter - 1);
    manager.removePlayer(temporaryPlayer);
  }

  @Test
  public void shouldRemoveOneAddedPlayer() {
    Player player = new Player(ROONEY_FIRSTNAME, ROONEY_LASTNAME, ROONEY_YOB, new FootballTeam());
    manager.addPlayer(player);
    int counterBeforeRemoveOnePlayer = manager.getAllPlayers().size();
    Player retrievedPlayer = manager.getAllPlayers().get(counterBeforeRemoveOnePlayer - 1);
    manager.removePlayer(retrievedPlayer);
    int playersCounter = manager.getAllPlayers().size();
    assertEquals(counterBeforeRemoveOnePlayer - 1, playersCounter);
  }

  @Test
  public void shouldFindCorrectlyPlayerById() {
    Player player = new Player(ROONEY_FIRSTNAME, ROONEY_LASTNAME, ROONEY_YOB, new FootballTeam());
    manager.addPlayer(player);
    long id = player.getId();
    Player retrievedPlayer = manager.findPlayerById(id);
    assertEquals(retrievedPlayer, player);
    manager.removePlayer(retrievedPlayer);
  }

  @Test
  public void shouldAddOneLeague() {
    int counterBeforeAddOneLeague = manager.getAllLeagues().size();
    League league = new League(ENGLAND_NAME, ENGLAND_COUNTRY, ENGLAND_YOE);
    manager.addLeague(league);
    int leaguesCounter = manager.getAllLeagues().size();
    assertEquals(counterBeforeAddOneLeague + 1, leaguesCounter);
    League temporaryLeague = manager.getAllLeagues().get(leaguesCounter - 1);
    manager.removeLeague(temporaryLeague);
  }

  @Test
  public void shouldGetAllLeagues() {
    int leaguesCounter = manager.getAllLeagues().size();
    assertThat(leaguesCounter, either(is(0)).or(is(1)));
  }

  @Test
  public void shouldUpdateAllFieldsLeague() {
    League league = new League(ENGLAND_NAME, ENGLAND_COUNTRY, ENGLAND_YOE);
    manager.addLeague(league);
    int leaguesCounter = manager.getAllLeagues().size();
    League retrievedLeague = manager.getAllLeagues().get(leaguesCounter - 1);
    retrievedLeague.setName(SPAIN_NAME);
    retrievedLeague.setCountry(SPAIN_COUNTRY);
    retrievedLeague.setYearOfEstablished(SPAIN_YOE);
    manager.updateLeague(retrievedLeague);
    retrievedLeague = manager.getAllLeagues().get(leaguesCounter - 1);
    assertEquals(retrievedLeague.getName(), SPAIN_NAME);
    assertEquals(retrievedLeague.getCountry(), SPAIN_COUNTRY);
    assertEquals(retrievedLeague.getYearOfEstablished(), SPAIN_YOE);
    League temporaryLeague = manager.getAllLeagues().get(leaguesCounter - 1);
    manager.removeLeague(temporaryLeague);
  }

  @Test
  public void shouldRemoveOneAddedLeague() {
    League league = new League(ENGLAND_NAME, ENGLAND_COUNTRY, ENGLAND_YOE);
    manager.addLeague(league);
    int counterBeforeRemoveOneLeague = manager.getAllLeagues().size();
    League retrievedLeague = manager.getAllLeagues().get(counterBeforeRemoveOneLeague - 1);
    manager.removeLeague(retrievedLeague);
    int leaguesCounter = manager.getAllLeagues().size();
    assertEquals(counterBeforeRemoveOneLeague - 1, leaguesCounter);
  }

  @Test
  public void shouldFindCorrectlyLeagueById() {
    League league = new League(ENGLAND_NAME, ENGLAND_COUNTRY, ENGLAND_YOE);
    manager.addLeague(league);
    long id = league.getId();
    League retrievedLeague = manager.findLeagueById(id);
    assertEquals(retrievedLeague, league);
    manager.removeLeague(retrievedLeague);
  }

  @Test
  public void shouldReturnCorrectlyNumberOfTeamsInLeague() {
    League league = new League();
    FootballTeam team1 = new FootballTeam();
    FootballTeam team2 = new FootballTeam();
    List<FootballTeam> footballTeams = new ArrayList<FootballTeam>();
    footballTeams.add(team1);
    footballTeams.add(team2);
    league.setFootballTeams(footballTeams);
    manager.addLeague(league);
    League retrievedLeague = manager.getAllLeagues().get(manager.getAllLeagues().size() - 1);
    assertEquals(retrievedLeague.getFootballTeams().size(), 2);
    manager.removeLeague(retrievedLeague);
  }

  @Test
  public void shouldReturnCorrectlyNumberOfPlayersInFootballTeam() {
    FootballTeam footballTeam = new FootballTeam();
    Player player1 = new Player();
    Player player2 = new Player();
    Player player3 = new Player();
    List<Player> players = new ArrayList<Player>();
    players.add(player1);
    players.add(player2);
    players.add(player3);
    footballTeam.setPlayers(players);
    manager.addFootballTeam(footballTeam);
    FootballTeam retrievedFootballTeam = manager.getAllFootballTeams().get(manager.getAllFootballTeams().size() - 1);
    assertEquals(retrievedFootballTeam.getPlayers().size(), 3);
    manager.removeFootballTeam(retrievedFootballTeam);
  }

}