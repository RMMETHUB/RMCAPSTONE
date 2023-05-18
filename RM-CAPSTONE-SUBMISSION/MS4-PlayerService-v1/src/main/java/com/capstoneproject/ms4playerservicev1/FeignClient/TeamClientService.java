package com.capstoneproject.ms4playerservicev1.FeignClient;

import com.capstoneproject.ms4playerservicev1.common.APIMapping;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name="ms5-team-service-v1",configuration = FeignClientConfiguration.class)
public interface TeamClientService {

    @GetMapping(APIMapping.GET_MAPPING_ENDPOINT_VALIDATE_TEAM_BY_ID)
    public Boolean validateById(@RequestParam Integer id);

}
