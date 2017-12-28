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
  public static void shouldOpenConnection() {
    assertNotNull(footballTeamService.openConnection());
  }

  @AfterClass
  public static void shouldCloseConnection() {
    assertNotNull(footballTeamService.closeConnection());
  }

  @Before
  public void shouldAddFourFootballTeams() {
    List<FootballTeam> footballTeamsInput = new ArrayList<FootballTeam>();
    footballTeamsInput.add(FCL);
    footballTeamsInput.add(MU);
    footballTeamsInput.add(FCB);
    footballTeamsInput.add(RM);
    int size = footballTeamService.addAllFootballTeamsFromList(footballTeamsInput);
    assertThat(size, either(is(0)).or(is(4)));
  }

  @After
  public void shouldRemoveFourFootballTeams() {
    footballTeamService.removeAllFootballTeams();
    assertTrue(footballTeamService.getAllFootballTeams().size() == 0);
  }

  @Test
  public void shouldCreateTableFootballTeam() {
    assertNotNull(footballTeamService.createTable());
  }

  @Test
  public void shouldCreateAllStatements() {
    assertNotNull(footballTeamService.createStatements());
  }

  @Test
  public void shouldGetCorrectConnection() {
    assertNotNull(footballTeamService.getConnection());
  }

  @Test
  public void shouldAddOneFootballTeam() {
    footballTeamService.addFootballTeam(EL);
    assertEquals(5, footballTeamService.getAllFootballTeams().size());
  }

  @Test
  public void shouldGetAllFootballTeams() {
    assertEquals(4, footballTeamService.getAllFootballTeams().size());
  }

  @Test
  public void shouldUpdateAllFieldsInFootballTeam() {
    String newName = "Arka Gdynia";
    int newYearOfEstablished = 1929;
    double newMarketValue = 1000000.99;
    footballTeams = footballTeamService.getAllFootballTeams();
    footballTeamService.updateFootballTeam(footballTeams.get(footballTeams.size() - 1), newName, newYearOfEstablished,
        newMarketValue);
    footballTeams = footballTeamService.getAllFootballTeams();
    assertEquals(newName, footballTeams.get(footballTeams.size() - 1).getName());
    assertEquals(newYearOfEstablished, footballTeams.get(footballTeams.size() - 1).getYearOfEstablished());
    assertEquals(newMarketValue, footballTeams.get(footballTeams.size() - 1).getMarketValue(), _PRECISION);
  }

  @Test
  public void shouldUpdateFootballTeamName() {
    String newName = "Legia Warsaw";
    footballTeams = footballTeamService.getAllFootballTeams();
    footballTeamService.updateFootballTeamName(footballTeams.get(0), newName);
    footballTeams = footballTeamService.getAllFootballTeams();
    assertEquals(newName, footballTeams.get(0).getName());
  }

  @Test
  public void shouldUpdateFootballTeamYearOfEstablished() {
    int newYearOfEstablished = 1916;
    footballTeams = footballTeamService.getAllFootballTeams();
    footballTeamService.updateFootballTeamYearOfEstablished(footballTeams.get(0), newYearOfEstablished);
    footballTeams = footballTeamService.getAllFootballTeams();
    assertEquals(newYearOfEstablished, footballTeams.get(0).getYearOfEstablished());
  }

  @Test
  public void shouldUpdateFootballTeamMarketValue() {
    double newMarketValue = 16000000.02;
    footballTeams = footballTeamService.getAllFootballTeams();
    footballTeamService.updateFootballTeamMarketValue(footballTeams.get(0), newMarketValue);
    footballTeams = footballTeamService.getAllFootballTeams();
    assertEquals(newMarketValue, footballTeams.get(0).getMarketValue(), _PRECISION);
  }

  @Test
  public void shouldRemoveAllFootballTeamsFromList() {
    List<FootballTeam> footballTeamsInput = new ArrayList<FootballTeam>();
    footballTeamsInput.add(FCL);
    footballTeamsInput.add(MU);
    int counter = footballTeamService.removeAllFootballTeamsFromList(footballTeamsInput);
    assertThat(counter, either(is(0)).or(is(2)));
  }

  @Test
  public void shouldRemoveFootballTeamById() {
    footballTeams = footballTeamService.getAllFootballTeams();
    FootballTeam tmp = footballTeams.get(0);
    footballTeamService.removeFootballTeamById(footballTeams.get(0).getId());
    footballTeams = footballTeamService.getAllFootballTeams();
    assertEquals(3, footballTeams.size());
    footballTeamService.addFootballTeam(tmp);
  }

  @Test
  public void shouldRemoveFootballTeamByName() {
    footballTeams = footballTeamService.getAllFootballTeams();
    FootballTeam tmp = footballTeams.get(0);
    footballTeamService.removeFootballTeamByName(footballTeams.get(0).getName());
    footballTeams = footballTeamService.getAllFootballTeams();
    assertEquals(3, footballTeams.size());
    footballTeamService.addFootballTeam(tmp);
  }

  @Test
  public void shouldGetFootballTeamFindById() {
    footballTeams = footballTeamService.getAllFootballTeams();
    FootballTeam tmp = footballTeams.get(0);
    FootballTeam footballTeamRetrieved = footballTeamService.findById(tmp.getId());
    assertEquals(tmp.getName(), footballTeamRetrieved.getName());
    assertEquals(tmp.getYearOfEstablished(), footballTeamRetrieved.getYearOfEstablished());
    assertEquals(tmp.getMarketValue(), footballTeamRetrieved.getMarketValue(), _PRECISION);
  }

  @Test
  public void shouldGetFootballTeamFindByName() {
    footballTeams = footballTeamService.getAllFootballTeams();
    FootballTeam tmp = footballTeams.get(0);
    FootballTeam footballTeamRetrieved = footballTeamService.findByName(tmp.getName());
    assertEquals(tmp.getName(), footballTeamRetrieved.getName());
    assertEquals(tmp.getYearOfEstablished(), footballTeamRetrieved.getYearOfEstablished());
    assertEquals(tmp.getMarketValue(), footballTeamRetrieved.getMarketValue(), _PRECISION);
  }

  @Test
  public void shouldGetFootballTeamFindByYearOfEstablished() {
    footballTeams = footballTeamService.getAllFootballTeams();
    FootballTeam tmp = footballTeams.get(0);
    FootballTeam footballTeamRetrieved = footballTeamService.findByYearOfEstablished(tmp.getYearOfEstablished());
    assertEquals(tmp.getName(), footballTeamRetrieved.getName());
    assertEquals(tmp.getYearOfEstablished(), footballTeamRetrieved.getYearOfEstablished());
    assertEquals(tmp.getMarketValue(), footballTeamRetrieved.getMarketValue(), _PRECISION);
  }

  @Test
  public void shouldGetFootballTeamFindByMarketValue() {
    footballTeams = footballTeamService.getAllFootballTeams();
    FootballTeam tmp = footballTeams.get(0);
    FootballTeam footballTeamRetrieved = footballTeamService.findByMarketValue(tmp.getMarketValue());
    assertEquals(tmp.getName(), footballTeamRetrieved.getName());
    assertEquals(tmp.getYearOfEstablished(), footballTeamRetrieved.getYearOfEstablished());
    assertEquals(tmp.getMarketValue(), footballTeamRetrieved.getMarketValue(), _PRECISION);
  }

}