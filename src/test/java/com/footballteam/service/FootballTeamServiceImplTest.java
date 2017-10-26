package com.footballteam.service;

import static org.junit.Assert.*;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import com.footballteam.domain.FootballTeam;
import com.footballteam.service.FootballTeamServiceImpl;

public class FootballTeamServiceImplTest {
  private static final double _PRECISION = 0.01;
  private static FootballTeamServiceImpl footballTeamService = new FootballTeamServiceImpl();
  private static List<FootballTeam> footballTeams = footballTeamService.getAllFootballTeams();
  private static FootballTeam FCL = new FootballTeam("FC Liverpool", 1892, 512000000.25);
  private static FootballTeam MU = new FootballTeam("Manchester United", 1878, 900000000.01);
  private static FootballTeam FCB = new FootballTeam("FC Barcelona", 1899, 612000000.28);
  private static FootballTeam RM = new FootballTeam("Real Madrid", 1902, 720000000.11);

  @BeforeClass
  public static void checkGetConnection() {
    assertNotNull(footballTeamService.getConnection());
  }

  @AfterClass
  public static void checkCloseConnection() {
    assertNotNull(footballTeamService.closeConnection());
  }

  @Before
  public void checkAddFootballTeam() {
    assertEquals(1, footballTeamService.addFootballTeam(FCL));
    assertEquals(1, footballTeamService.addFootballTeam(MU));
    assertEquals(1, footballTeamService.addFootballTeam(FCB));
    assertEquals(1, footballTeamService.addFootballTeam(RM));
  }

  @After
  public void checkRemoveAllFootballTeams() {
    footballTeamService.removeAllFootballTeams();
    assertTrue(footballTeamService.getAllFootballTeams().size() == 0);
  }

  @Test
  public void checkGetAllFootballTeams() {
    assertEquals(4, footballTeamService.getAllFootballTeams().size());
  }

  @Test
  public void checkUpdateFootballTeamName() {
    String newName = "Legia Warsaw";
    footballTeams = footballTeamService.getAllFootballTeams();
    footballTeamService.updateFootballTeamName(footballTeams.get(0), newName);
    footballTeams = footballTeamService.getAllFootballTeams();
    assertEquals(newName, footballTeams.get(0).getName());
  }

  @Test
  public void checkUpdateFootballTeamYearOfEstablished() {
    int newYearOfEstablished = 1916;
    footballTeams = footballTeamService.getAllFootballTeams();
    footballTeamService.updateFootballTeamYearOfEstablished(footballTeams.get(0), newYearOfEstablished);
    footballTeams = footballTeamService.getAllFootballTeams();
    assertEquals(newYearOfEstablished, footballTeams.get(0).getYearOfEstablished());
  }

  @Test
  public void checkUpdateFootballTeamMarketValue() {
    double newMarketValue = 16000000.02;
    footballTeams = footballTeamService.getAllFootballTeams();
    footballTeamService.updateFootballTeamMarketValue(footballTeams.get(0), newMarketValue);
    footballTeams = footballTeamService.getAllFootballTeams();
    assertEquals(newMarketValue, footballTeams.get(0).getMarketValue(), _PRECISION);
  }

  @Test
  public void checkRemoveFootballTeamById() {
    footballTeams = footballTeamService.getAllFootballTeams();
    FootballTeam tmp = footballTeams.get(0);
    footballTeamService.removeFootballTeamById(footballTeams.get(0).getId());
    footballTeams = footballTeamService.getAllFootballTeams();
    assertEquals(3, footballTeams.size());
    footballTeamService.addFootballTeam(tmp);
  }

  @Test
  public void checkRemoveFootballTeamByName() {
    footballTeams = footballTeamService.getAllFootballTeams();
    FootballTeam tmp = footballTeams.get(0);
    footballTeamService.removeFootballTeamByName(footballTeams.get(0).getName());
    footballTeams = footballTeamService.getAllFootballTeams();
    assertEquals(3, footballTeams.size());
    footballTeamService.addFootballTeam(tmp);
  }

  @Test
  public void checkFindById() {
    footballTeams = footballTeamService.getAllFootballTeams();
    FootballTeam tmp = footballTeams.get(0);
    FootballTeam footballTeamRetrieved = footballTeamService.findById(tmp.getId());
    assertEquals(tmp.getName(), footballTeamRetrieved.getName());
    assertEquals(tmp.getYearOfEstablished(), footballTeamRetrieved.getYearOfEstablished());
    assertEquals(tmp.getMarketValue(), footballTeamRetrieved.getMarketValue(), _PRECISION);
  }

  @Test
  public void checkFindByName() {
    footballTeams = footballTeamService.getAllFootballTeams();
    FootballTeam tmp = footballTeams.get(0);
    FootballTeam footballTeamRetrieved = footballTeamService.findByName(tmp.getName());
    assertEquals(tmp.getName(), footballTeamRetrieved.getName());
    assertEquals(tmp.getYearOfEstablished(), footballTeamRetrieved.getYearOfEstablished());
    assertEquals(tmp.getMarketValue(), footballTeamRetrieved.getMarketValue(), _PRECISION);
  }

  @Test
  public void checkFindByYearOfEstablished() {
    footballTeams = footballTeamService.getAllFootballTeams();
    FootballTeam tmp = footballTeams.get(0);
    FootballTeam footballTeamRetrieved = footballTeamService.findByYearOfEstablished(tmp.getYearOfEstablished());
    assertEquals(tmp.getName(), footballTeamRetrieved.getName());
    assertEquals(tmp.getYearOfEstablished(), footballTeamRetrieved.getYearOfEstablished());
    assertEquals(tmp.getMarketValue(), footballTeamRetrieved.getMarketValue(), _PRECISION);
  }

  @Test
  public void checkFindByMarketValue() {
    footballTeams = footballTeamService.getAllFootballTeams();
    FootballTeam tmp = footballTeams.get(0);
    FootballTeam footballTeamRetrieved = footballTeamService.findByMarketValue(tmp.getMarketValue());
    assertEquals(tmp.getName(), footballTeamRetrieved.getName());
    assertEquals(tmp.getYearOfEstablished(), footballTeamRetrieved.getYearOfEstablished());
    assertEquals(tmp.getMarketValue(), footballTeamRetrieved.getMarketValue(), _PRECISION);
  }

}