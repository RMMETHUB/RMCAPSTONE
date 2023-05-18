package com.capstoneproject.ms6tournamentservicev1;

import com.capstoneproject.ms6tournamentservicev1.tournament.Tournament;
import com.capstoneproject.ms6tournamentservicev1.tournament.TournamentDTO;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TournamentEntityTest {

    @Test
    public void initializingTournamentEntityTest(){
        Tournament tournamentEntity = mockTournamentRequest();
        Tournament expectedResult = new Tournament(1,"testTournamentName","testCategory","testTournamentStyle",true);
        Assert.assertEquals(expectedResult.getId(), tournamentEntity.getId());
        Assert.assertEquals(expectedResult.getTournamentName(), tournamentEntity.getTournamentName());
        Assert.assertEquals(expectedResult.getTournamentStyle(), tournamentEntity.getTournamentStyle());
        Assert.assertEquals(expectedResult.getSportsCategory(), tournamentEntity.getSportsCategory());
        Assert.assertEquals(expectedResult.isActive(), tournamentEntity.isActive());
    }

    @Test(expected = NullPointerException.class)
    public void whenFieldIdIsNull_return_an_Exception(){
        Tournament fieldEntity  = new Tournament();
        fieldEntity.setId((Long)null);
    }

    @Test
    public void toStringTest(){
        String tournamentEntityString = "Tournament{id=1, tournamentName='test', sportsCategory='testAddress', tournamentStyle='testTournamentStyle', isActive=true}";
        Tournament expectedResult = new Tournament(1,"test","testAddress","testTournamentStyle",true);
        Assert.assertEquals(tournamentEntityString,expectedResult.toString());
    }

    private Tournament mockTournamentRequest(){
        Tournament tournament = new Tournament();
        tournament.setId(1);
        tournament.setTournamentName("testTournamentName");
        tournament.setTournamentStyle("testTournamentStyle");
        tournament.setSportsCategory("testCategory");
        tournament.setActive(true);
        tournament.setActive(true);
        return tournament;
    }
}
