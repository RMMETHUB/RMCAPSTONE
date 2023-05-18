package com.capstoneproject.ms7matchservicev1.fiegn_client.tournament;

import com.capstoneproject.ms7matchservicev1.common.APIMapping;
import com.capstoneproject.ms7matchservicev1.fiegn_client.FeignClientConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name="ms6-tournament-service-v1",configuration = FeignClientConfiguration.class)
public interface TournamentClientService {

    @GetMapping(APIMapping.GET_MAPPING_ENDPOINT_GET_TOURNAMENT_BY_ID)
    public TournamentDTO getTournament(@RequestParam Long id);

    @GetMapping(APIMapping.GET_MAPPING_ENDPOINT_VALIDATE_TOURNAMENT_BY_ID)
    public Boolean validateTournamentById(@RequestParam Long id);

}
