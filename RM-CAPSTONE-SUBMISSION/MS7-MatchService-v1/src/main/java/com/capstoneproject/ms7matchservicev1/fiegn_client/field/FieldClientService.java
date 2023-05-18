package com.capstoneproject.ms7matchservicev1.fiegn_client.field;

import com.capstoneproject.ms7matchservicev1.common.APIMapping;
import com.capstoneproject.ms7matchservicev1.fiegn_client.FeignClientConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name="ms8-field-service-v1",configuration = FeignClientConfiguration.class)
public interface FieldClientService {


    @GetMapping(APIMapping.GET_MAPPING_ENDPOINT_GET_FIELD_BY_ID)
    public FieldDTO getFieldById(@RequestParam Integer id);

    @GetMapping(APIMapping.GET_MAPPING_ENDPOINT_VALIDATE_FIELD_BY_ID)
    public Boolean validateFieldBYId(@RequestParam Integer id);

}
