package com.capstoneproject.ms7matchservicev1.fiegn_client.team;

import com.capstoneproject.ms7matchservicev1.common.APIMapping;
import com.capstoneproject.ms7matchservicev1.fiegn_client.FeignClientConfiguration;
import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name="ms5-team-service-v1",configuration = FeignClientConfiguration.class)
public interface TeamClientService {
    @Headers({"X-Forwarded-Host: localhost:8081"})
    @GetMapping(APIMapping.GET_MAPPING_ENDPOINT_GET_TEAM_BY_ID)
    public TeamResponseModel getTeamById(@RequestParam Integer id);
}
