package com.capstoneproject.ms5teamservice.FeignClient;

import com.capstoneproject.ms5teamservice.common.APIMapping;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
@FeignClient(name="ms4-player-service-v1",configuration = FeignClientConfiguration.class)
public interface PlayeClientService {

    @GetMapping(APIMapping.GET_MAPPING_ENDPOINT_GET_PLAYE_BY_TEAM_ID)
    public List<PlayerRequestModel> getPlayerByTeamId(@RequestParam Integer id);
}
