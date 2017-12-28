package com.footballteam.service;

import static org.hamcrest.CoreMatchers.either;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

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
  public void addFootballTeam_TEST() {
    FootballTeam footballTeam = new FootballTeam();
    footballTeam.setName(NAME_1);
    footballTeam.setYearOfEstablished(YOE_1);
    footballTeam.setMarketValue(MV_1);
    footballTeamManager.addFootballTeam(footballTeam);
    int counter = footballTeamManager.getAllFootballTeams().size();
    assertEquals(counter, 1);
    assertTrue(true);
  }

  @Test
  public void getAllFootballTeams_TEST() {
    int counter = footballTeamManager.getAllFootballTeams().size();
    assertThat(counter, either(is(0)).or(is(1)));
    assertTrue(true);
  }

  @Test
  public void updateFootballTeam_TEST() {
    FootballTeam footballTeam = footballTeamManager.getAllFootballTeams().get(0);
    footballTeam.setName(UPD_NAME_1);
    footballTeam.setYearOfEstablished(UPD_YOE_1);
    footballTeam.setMarketValue(UPD_MV_1);
    footballTeamManager.updateFootballTeam(footballTeam);
    footballTeam = footballTeamManager.getAllFootballTeams().get(0);
    assertEquals(footballTeam.getName(), UPD_NAME_1);
    assertEquals(footballTeam.getYearOfEstablished(), UPD_YOE_1);
    assertEquals(footballTeam.getMarketValue(), UPD_MV_1, 0.01);
    assertTrue(true);
  }

  @Test
  public void deleteFootballTeam_TEST() {
    FootballTeam footballTeam = footballTeamManager.getAllFootballTeams().get(0);
    footballTeamManager.deleteFootballTeam(footballTeam);
    int counter = footballTeamManager.getAllFootballTeams().size();
    assertEquals(counter, 0);
    assertTrue(true);
  }

}