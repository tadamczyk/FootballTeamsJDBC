package com.footballteam.service;

import static org.hamcrest.CoreMatchers.either;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import java.util.ArrayList;
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
  private static List<FootballTeam> footballTeams = new ArrayList<FootballTeam>();
  private static FootballTeam FCL = new FootballTeam("FC Liverpool", 1892, 512000000.25);
  private static FootballTeam MU = new FootballTeam("Manchester United", 1878, 900000000.01);
  private static FootballTeam FCB = new FootballTeam("FC Barcelona", 1899, 612000000.28);
  private static FootballTeam RM = new FootballTeam("Real Madrid", 1902, 720000000.11);
  private static FootballTeam EL = new FootballTeam("Everton Liverpool", 1878, 412000000.25);

  @BeforeClass
  public static void checkOpenConnection() {
    assertNotNull(footballTeamService.openConnection());
  }

  // hidden
  @AfterClass
  public static void checkCloseConnection() {
    assertNotNull(footballTeamService.closeConnection());
  }

  // @BeforeClass and static method
  @Before
  public void checkAddAllFootballTeam() {
    List<FootballTeam> footballTeamsInput = new ArrayList<FootballTeam>();
    footballTeamsInput.add(FCL);
    footballTeamsInput.add(MU);
    footballTeamsInput.add(FCB);
    footballTeamsInput.add(RM);
    int size = footballTeamService.addAllFootballTeamsFromList(footballTeamsInput);
    assertThat(size, either(is(0)).or(is(4)));
  }

  // @Ignore
  @After
  public void checkRemoveAllFootballTeams() {
    footballTeamService.removeAllFootballTeams();
    assertTrue(footballTeamService.getAllFootballTeams().size() == 0);
  }

  @Test
  public void checkCreateTable() {
    assertNotNull(footballTeamService.createTable());
  }

  @Test
  public void checkCreateStatements() {
    assertNotNull(footballTeamService.createStatements());
  }

  @Test
  public void checkGetConnection() {
    assertNotNull(footballTeamService.getConnection());
  }

  @Test
  public void checkAddFootballTeam() {
    footballTeamService.addFootballTeam(EL);
    assertEquals(5, footballTeamService.getAllFootballTeams().size());
  }

  @Test
  public void checkGetAllFootballTeams() {
    assertEquals(4, footballTeamService.getAllFootballTeams().size());
  }

  @Test
  public void checkUpdateFootballTeam() {
    String newName = "Arka Gdynia";
    int newYearOfEstablished = 1929;
    double newMarketValue = 1000000.99;
    footballTeams = footballTeamService.getAllFootballTeams();
    footballTeamService.updateFootballTeamName(footballTeams.get(footballTeams.size() - 1), newName);
    footballTeamService.updateFootballTeamYearOfEstablished(footballTeams.get(footballTeams.size() - 1),
        newYearOfEstablished);
    footballTeamService.updateFootballTeamMarketValue(footballTeams.get(footballTeams.size() - 1), newMarketValue);
    footballTeams = footballTeamService.getAllFootballTeams();
    assertEquals(newName, footballTeams.get(footballTeams.size() - 1).getName());
    assertEquals(newYearOfEstablished, footballTeams.get(footballTeams.size() - 1).getYearOfEstablished());
    assertEquals(newMarketValue, footballTeams.get(footballTeams.size() - 1).getMarketValue(), _PRECISION);
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