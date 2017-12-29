package com.footballteam.service;

import static org.hamcrest.CoreMatchers.either;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.footballteam.domain.FootballTeam;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/beans.xml" })
@TransactionConfiguration(transactionManager = "txManager", defaultRollback = false)
@Transactional
public class FootballTeamManagerTest {

  private final String NAME_1 = "FC Liverpool";
  private final int YOE_1 = 1892;
  private final double MV_1 = 512000000.01;

  private final String UPD_NAME_1 = "FC Everton";
  private final int UPD_YOE_1 = 1878;
  private final double UPD_MV_1 = 364000000.50;

  @Autowired
  FootballTeamManager footballTeamManager;

  @Test
  public void shouldAddOneFootballTeam() {
    int counterBeforeAddOneFootballTeam = footballTeamManager.getAllFootballTeams().size();
    FootballTeam footballTeam = new FootballTeam(NAME_1, YOE_1, MV_1);
    footballTeamManager.addFootballTeam(footballTeam);
    int footballTeamsCounter = footballTeamManager.getAllFootballTeams().size();
    assertEquals(counterBeforeAddOneFootballTeam + 1, footballTeamsCounter);
    FootballTeam temporaryFootballTeam = footballTeamManager.getAllFootballTeams().get(footballTeamsCounter - 1);
    footballTeamManager.removeFootballTeam(temporaryFootballTeam);
  }

  @Test
  public void shouldGetAllFootballTeams() {
    int footballTeamsCounter = footballTeamManager.getAllFootballTeams().size();
    assertThat(footballTeamsCounter, either(is(0)).or(is(1)));
  }

  @Test
  public void shouldUpdateAllFieldsFootballTeam() {
    FootballTeam footballTeam = new FootballTeam("Arka Gdynia", 1929, 6500000);
    footballTeamManager.addFootballTeam(footballTeam);
    int footballTeamsCounter = footballTeamManager.getAllFootballTeams().size();
    FootballTeam retrievedFootballTeam = footballTeamManager.getAllFootballTeams().get(footballTeamsCounter - 1);
    retrievedFootballTeam.setName(UPD_NAME_1);
    retrievedFootballTeam.setYearOfEstablished(UPD_YOE_1);
    retrievedFootballTeam.setMarketValue(UPD_MV_1);
    footballTeamManager.updateFootballTeam(retrievedFootballTeam);
    retrievedFootballTeam = footballTeamManager.getAllFootballTeams().get(footballTeamsCounter - 1);
    assertEquals(retrievedFootballTeam.getName(), UPD_NAME_1);
    assertEquals(retrievedFootballTeam.getYearOfEstablished(), UPD_YOE_1);
    assertEquals(retrievedFootballTeam.getMarketValue(), UPD_MV_1, 0.01);
    FootballTeam temporaryFootballTeam = footballTeamManager.getAllFootballTeams().get(footballTeamsCounter - 1);
    footballTeamManager.removeFootballTeam(temporaryFootballTeam);
  }

  @Test
  public void shouldRemoveOneAddedFootballTeam() {
    FootballTeam footballTeam = new FootballTeam("FC Barcelona", 1899, 650000000);
    footballTeamManager.addFootballTeam(footballTeam);
    int footballTeamsCounter = footballTeamManager.getAllFootballTeams().size();
    FootballTeam retrievedFootballTeam = footballTeamManager.getAllFootballTeams().get(footballTeamsCounter - 1);
    footballTeamManager.removeFootballTeam(retrievedFootballTeam);
    footballTeamsCounter = footballTeamManager.getAllFootballTeams().size();
    assertThat(footballTeamsCounter, either(is(0)).or(is(1)));
  }

}