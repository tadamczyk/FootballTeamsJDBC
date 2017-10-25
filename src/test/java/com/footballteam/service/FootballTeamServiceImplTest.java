package com.footballteam.service;

import static org.junit.Assert.*;
import java.util.List;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import com.footballteam.domain.FootballTeam;
import com.footballteam.service.FootballTeamServiceImpl;

public class FootballTeamServiceImplTest {
  static FootballTeamServiceImpl footballTeamService = new FootballTeamServiceImpl();

  FootballTeam FCL = new FootballTeam("FC Liverpool", 1892, 512000000.25);
  FootballTeam MU = new FootballTeam("Manchester United", 1878, 900000000.01);

  @BeforeClass
  public static void checkConnection() {
    assertNotNull(footballTeamService.getConnection());
  }

  @Test
  public void checkAddFootballTeam() {
    int result = 0;
    result += footballTeamService.addFootballTeam(FCL);
    result += footballTeamService.addFootballTeam(MU);
    assertEquals(2, result);
  }

  @Test
  public void checkGetAllFootballTeams() {
    List<FootballTeam> footballTeams = footballTeamService.showAllFootballTeams();
    FootballTeam footballTeamRetrieved = footballTeams.get(0);
    assertEquals(footballTeams.get(0).getName(), footballTeamRetrieved.getName());
    assertEquals(footballTeams.get(0).getYearOfEstablished(), footballTeamRetrieved.getYearOfEstablished());
    assertEquals(footballTeams.get(0).getMarketValue(), footballTeamRetrieved.getMarketValue(), 0.01);
  }

  @Test
  public void checkUpdateFootballTeamName() {
    int result = footballTeamService.updateFootballTeamName(FCL, "Legia Warszawa");
    assertEquals(1, result);
  }

  @Test
  public void checkUpdateFootballTeamYearOfEstablished() {
    int result = footballTeamService.updateFootballTeamYearOfEstablished(FCL, 1916);
    assertEquals(1, result);
  }

  @Test
  public void checkUpdateFootballTeamMarketValue() {
    int result = footballTeamService.updateFootballTeamMarketValue(FCL, 16000000.01);
    assertEquals(1, result);
  }

  @Ignore
  public static void checkRemoveAllFootballTeams() {
    footballTeamService.removeAllFootballTeams();
    assertEquals(1, footballTeamService.showAllFootballTeams());
  }

  @Test
  public void checkRemoveFootballTeamById() {
    // TODO
  }

  @Test
  public void checkRemoveFootballTeamByName() {
    int result = footballTeamService.removeFootballTeamByName(MU.getName());
    assertEquals(1, result);
  }

  @Test
  public void checkFindById() {
    // TODO
  }

  @Test
  public void checkFindByName() {
    FootballTeam footballTeamRetrieved = footballTeamService.findByName(FCL.getName());
    assertEquals(FCL.getName(), footballTeamRetrieved.getName());
  }

  @Test
  public void checkFindByYearOfEstablished() {
    FootballTeam footballTeamRetrieved = footballTeamService.findByYearOfEstablished(FCL.getYearOfEstablished());
    assertEquals(FCL.getYearOfEstablished(), footballTeamRetrieved.getYearOfEstablished());
  }

  @Test
  public void checkFindByMarketValue() {
    FootballTeam footballTeamRetrieved = footballTeamService.findByMarketValue(FCL.getMarketValue());
    assertEquals(FCL.getMarketValue(), footballTeamRetrieved.getMarketValue(), 0.01);
  }

}