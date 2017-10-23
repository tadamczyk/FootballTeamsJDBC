package com.tomasz.service;

import static org.junit.Assert.*;
import java.util.List;
import org.junit.Test;
import com.tomasz.domain.FootballTeam;
import com.tomasz.service.FootballTeamService;

public class FootballTeamServiceTest {
	FootballTeamService footballTeamService = new FootballTeamService();

	private final static String NAME_1 = "FC Liverpool";
	private final static int YOE_1 = 1892;
	private final static double VALUE_1 = 512000000.23;

	@Test
	public void checkConnection() {
		assertNotNull(footballTeamService.getConnection());
	}

	@Test
	public void checkAdding() {
		FootballTeam footballTeam = new FootballTeam(NAME_1, YOE_1, VALUE_1);
		footballTeamService.removeAllFootballTeams();
		assertEquals(1, footballTeamService.addFootballTeam(footballTeam));
		List<FootballTeam> footballTeams = footballTeamService.getAllFootballTeams();
		FootballTeam footballTeamRetrieved = footballTeams.get(0);
		assertEquals(NAME_1, footballTeamRetrieved.getName());
		assertEquals(YOE_1, footballTeamRetrieved.getYearOfEstablished());
		assertEquals(VALUE_1, footballTeamRetrieved.getMarketValue(), 0.01);
	}
}
