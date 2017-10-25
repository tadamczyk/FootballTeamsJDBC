package com.tomasz.service;

import static org.junit.Assert.*;
import java.util.List;
import org.junit.Test;
import com.tomasz.domain.FootballTeam;
import com.tomasz.service.FootballTeamService;

public class FootballTeamServiceTest {
  FootballTeamService footballTeamService = new FootballTeamService();

  private final static String NAME_1 = "FC Liverpool";
  private final static int YEAR_OF_ESTABLISHED_1 = 1892;
  private final static double MARKET_VALUE_1 = 512000000.23;

  @Test
  public void checkConnection() {
    assertNotNull(footballTeamService.getConnection());
  }

  @Test
  public void checkAdding() {
    FootballTeam footballTeam = new FootballTeam(NAME_1, YEAR_OF_ESTABLISHED_1, MARKET_VALUE_1);
    footballTeamService.removeAllFootballTeams();
    assertEquals(1, footballTeamService.addFootballTeam(footballTeam));
    List<FootballTeam> footballTeams = footballTeamService.getAllFootballTeams();
    FootballTeam footballTeamRetrieved = footballTeams.get(0);
    assertEquals(NAME_1, footballTeamRetrieved.getName());
    assertEquals(YEAR_OF_ESTABLISHED_1, footballTeamRetrieved.getYearOfEstablished());
    assertEquals(MARKET_VALUE_1, footballTeamRetrieved.getMarketValue(), 0.01);
  }
}
