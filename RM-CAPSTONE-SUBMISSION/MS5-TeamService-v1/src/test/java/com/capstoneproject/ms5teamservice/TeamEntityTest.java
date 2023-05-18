package com.capstoneproject.ms5teamservice;

import com.capstoneproject.ms5teamservice.Team.TeamEntity;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TeamEntityTest {

    @Test
    public void initializingTeamEntityTest(){
        TeamEntity teamEntity = mockTeamEntity();
        TeamEntity expectedResult = new TeamEntity(1,"testTeam",true);
        Assert.assertEquals(expectedResult.getId(), teamEntity.getId());
        Assert.assertEquals(expectedResult.getTeamName(), teamEntity.getTeamName());
        Assert.assertEquals(expectedResult.isActive(), teamEntity.isActive());
    }

    @Test(expected = NullPointerException.class)
    public void whenTeamIdIsNull_return_an_Exception(){
        TeamEntity teamEntity = new TeamEntity();
        teamEntity.setId((Integer)null);
    }

    @Test
    public void toStringTest(){
        String teamEntityString = "TeamEntity{id=1, teamName='test', isActive=true}";
        TeamEntity teamEntity = new TeamEntity(1, "test", true);
        Assert.assertEquals(teamEntityString,teamEntity.toString());
    }

    private TeamEntity mockTeamEntity(){
        TeamEntity teamEntity = new TeamEntity();
        teamEntity.setId(1);
        teamEntity.setTeamName("testTeam");
        teamEntity.setActive(true);
        return teamEntity;
    }

}
