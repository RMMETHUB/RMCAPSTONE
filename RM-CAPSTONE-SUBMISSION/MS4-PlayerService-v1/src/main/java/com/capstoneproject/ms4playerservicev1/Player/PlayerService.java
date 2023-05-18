package com.capstoneproject.ms4playerservicev1.Player;
import java.util.List;

public interface PlayerService {

    public boolean addPlayer(PlayerRequestModel playerRequestModel);
    public boolean updatePlayer(PlayerRequestModel playerRequestModel);
    public boolean deletePlayerById(long id);
    public PlayerRequestModel getPlayerById(long id);
    public List<PlayerRequestModel> getPlayerByTeamId(int id);
    public List<PlayerRequestModel> getPlayer();
}
