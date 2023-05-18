package com.capstoneproject.ms7matchservicev1;


import com.capstoneproject.ms7matchservicev1.match.MatchEntity;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;


@SpringBootTest
public class MatchEntityTest {

    @Test
    public void initializingMatchEntityTest(){
        MatchEntity matchEntity = mockMatchEntity();
        MatchEntity expectedResult = new MatchEntity(1,1,new Date(),1,1,1,true);
        Assert.assertEquals(expectedResult.getId(), matchEntity.getId());
        Assert.assertEquals(expectedResult.getFieldId(), matchEntity.getFieldId());
        Assert.assertEquals(expectedResult.getTournamentId(), matchEntity.getTournamentId());
        Assert.assertEquals(expectedResult.getTeamAId(), matchEntity.getTeamAId());
        Assert.assertEquals(expectedResult.getTeamBId(), matchEntity.getTeamBId());
        Assert.assertEquals(expectedResult.getDateTime(), matchEntity.getDateTime());
        Assert.assertEquals(expectedResult.isActive(), matchEntity.isActive());
    }

    @Test(expected = NullPointerException.class)
    public void whenMatchIdIsNull_return_an_Exception(){
        MatchEntity matchEntity  = new MatchEntity();
        matchEntity.setId((Long)null);
    }

    @Test
    public void toStringTest(){
        String matchEntityString = "MatchEntity{id=1, fieldId=1, dateTime=Tue May 16 10:06:41 SGT 2023, tournamentId=1, teamAId=1, teamBId=1, isActive=true}";
        MatchEntity matchEntity = new MatchEntity(1,1,new Date(1684202801000L),1,1,1,true);
        Assert.assertEquals(matchEntityString,matchEntity.toString());
    }

    private MatchEntity mockMatchEntity(){
        MatchEntity matchEntity = new MatchEntity();
        matchEntity.setId(1);
        matchEntity.setFieldId(1);
        matchEntity.setTournamentId(1);
        matchEntity.setTeamAId(1);
        matchEntity.setTeamBId(1);
        matchEntity.setDateTime(new Date());
        matchEntity.setActive(true);
        return matchEntity;
    }


}
