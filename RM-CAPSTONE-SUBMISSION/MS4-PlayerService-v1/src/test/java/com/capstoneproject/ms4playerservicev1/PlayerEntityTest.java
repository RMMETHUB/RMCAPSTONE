package com.capstoneproject.ms4playerservicev1;

import com.capstoneproject.ms4playerservicev1.Player.PlayerEntity;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class PlayerEntityTest {


    @Test
    public void initializingPlayerEntityTest(){
        PlayerEntity expectedResult = new PlayerEntity();
        PlayerEntity playerEntityToTest = mockPlayerEntity();
        expectedResult.setId(1);
        expectedResult.setFirstName("test");
        expectedResult.setLastName("test");
        expectedResult.setCountry("test");
        expectedResult.setTeamId(1);
        expectedResult.setActive(true);
        Assert.assertEquals(expectedResult.getId(), playerEntityToTest.getId());
        Assert.assertEquals(expectedResult.getFirstName(), playerEntityToTest.getFirstName());
        Assert.assertEquals(expectedResult.getLastName(), playerEntityToTest.getLastName());
        Assert.assertEquals(expectedResult.getCountry(), playerEntityToTest.getCountry());
        Assert.assertEquals(expectedResult.getTeamId(), playerEntityToTest.getTeamId());
        Assert.assertEquals(expectedResult.isActive(), playerEntityToTest.isActive());
    }

    @Test(expected = NullPointerException.class)
    public void whenPlayerIdIsNull_return_an_Exception(){
        PlayerEntity playerEntity = new PlayerEntity();
        playerEntity.setId((Long)null);
    }
    @Test(expected = NullPointerException.class)
    public void whenPlayerFirstNameIsNull_return_an_Exception(){
        PlayerEntity playerEntity = new PlayerEntity();
        playerEntity.setFirstName(null);
    }
    @Test(expected = NullPointerException.class)
    public void whenPlayerLastNameIsNull_return_an_Exception(){
        PlayerEntity playerEntity = new PlayerEntity();
        playerEntity.setLastName(null);
    }
    @Test(expected = NullPointerException.class)
    public void whenPlayerTeamIdIsNull_return_an_Exception(){
        PlayerEntity playerEntity = new PlayerEntity();
        playerEntity.setTeamId((Integer)null);
    }
    @Test(expected = NullPointerException.class)
    public void whenPlayerStatusIsNull_return_an_Exception(){
        PlayerEntity playerEntity = new PlayerEntity();
        playerEntity.setActive((Boolean)null);
    }
    @Test(expected = NullPointerException.class)
    public void whenPlayerCountryIsNull_return_an_Exception(){
        PlayerEntity playerEntity = new PlayerEntity();
        playerEntity.setCountry(null);
    }

    @Test
    public void toStringTest(){
        String playerEntityString = "PlayerEntity{id=1, firstName='test', lastName='test', country='test', isActive=true, teamId=1}";
        PlayerEntity playerEntity = new PlayerEntity(1, "test", "test", "test", true, 1);
        System.out.println(playerEntity.toString());
        //Assert.assertEquals(playerEntityString,playerEntity.toString());
    }

    private PlayerEntity mockPlayerEntity(){
        PlayerEntity playerEntity = new PlayerEntity();
        playerEntity.setId(1);
        playerEntity.setFirstName("test");
        playerEntity.setLastName("test");
        playerEntity.setCountry("test");
        playerEntity.setTeamId(1);
        playerEntity.setActive(true);
        return playerEntity;
    }

}
