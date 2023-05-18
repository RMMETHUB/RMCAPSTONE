package com.capstoneproject.ms9ticketservicev1.feign_client;

import com.capstoneproject.ms9ticketservicev1.common.APIMapping;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name="ms7-match-service-v1",configuration = FeignClientConfiguration.class)
public interface MatchClientService {

    @GetMapping(APIMapping.GET_MAPPING_ENDPOINT_GET_MATCH_REQUEST_BY_ID)
    public MatchResponseForTicketRequestModel getMatchRequestById(@RequestParam Long id);

    @GetMapping(APIMapping.GET_MAPPING_ENDPOINT_VALIDATE_MATCH_BY_ID)
    public Boolean validateById(@RequestParam Long id);
}
