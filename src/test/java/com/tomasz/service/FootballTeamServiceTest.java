package com.tomasz.service;

import static org.junit.Assert.*;
import java.util.List;
import org.junit.Test;
import com.tomasz.domain.FootballTeam;
import com.tomasz.service.FootballTeamService;

public class FootballTeamServiceTest {
	FootballTeamService footballTeamService = new FootballTeamService();
	
	@Test
	public void checkConnection(){
		assertNotNull(footballTeamService.getConnection());
	}

}
