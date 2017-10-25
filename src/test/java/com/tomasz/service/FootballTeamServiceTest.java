package com.tomasz.service;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;
import org.junit.Test;
import com.tomasz.domain.FootballTeam;
import com.tomasz.service.FootballTeamService;

public class FootballTeamServiceTest {
  FootballTeamService footballTeamService = new FootballTeamService();

  private final static String NAME_FCL = "FC Liverpool";
  private final static int YEAR_OF_ESTABLISHED_FCL = 1892;
  private final static double MARKET_VALUE_FCL = 512000000.23;
  FootballTeam FCL = new FootballTeam(NAME_FCL, YEAR_OF_ESTABLISHED_FCL, MARKET_VALUE_FCL);

  @Test
  public void checkConnection() {
    assertNotNull(footballTeamService.getConnection());
  }

  @Test
  public void checkAddFootballTeam() {
    int result = footballTeamService.addFootballTeam(FCL);
    assertEquals(1, result);
  }

  @Test
  public void checkFindById() {
    FootballTeam footballTeamRetrieved = footballTeamService.findById(FCL.getId());
    assertEquals(FCL.getId(), footballTeamRetrieved.getId());
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

  /*@After
  public void checkRemoveAllFootballTeams() {
    footballTeamService.removeAllFootballTeams();
    assertEquals(Arrays.asList(), footballTeamService.getAllFootballTeams());
  }*/

}
