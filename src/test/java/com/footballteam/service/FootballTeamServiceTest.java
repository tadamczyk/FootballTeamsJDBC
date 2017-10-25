package com.footballteam.service;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;
import org.junit.Test;
import com.footballteam.domain.FootballTeam;
import com.footballteam.service.FootballTeamService;

public class FootballTeamServiceTest {
  FootballTeamService footballTeamService = new FootballTeamService();

  FootballTeam FCL = new FootballTeam("FC Liverpool", 1892, 512000000.25);
  FootballTeam MU = new FootballTeam("Manchester United", 1878, 900000000.01);

  @Test
  public void checkConnection() {
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
  public void checkRemoveFootballTeamByName() {
    int result = footballTeamService.removeFootballTeamByName(MU.getName());
    assertEquals(1, result);
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

  /*
   * @After public void checkRemoveAllFootballTeams() {
   * footballTeamService.removeAllFootballTeams(); assertEquals(Arrays.asList(),
   * footballTeamService.getAllFootballTeams()); }
   */

}
