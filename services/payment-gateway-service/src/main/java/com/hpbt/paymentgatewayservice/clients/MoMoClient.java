package com.hpbt.paymentgatewayservice.clients;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

@FeignClient(name = "momo", url = "${momo.url.sandbox}")
public interface MoMoClient {
    @PostMapping(value = "${momo.path.create}", consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<String> createMoMo(@RequestBody Map<String, Object> request);

    @PostMapping("${momo.path.query}")
    ResponseEntity<String>  queryMoMo(@RequestBody Map<String, Object> request);

    @PostMapping("${momo.path.confirm}")
    ResponseEntity<String>  confirmMoMo(@RequestBody Map<String, Object> request);

    @PostMapping("${momo.path.refund}")
    ResponseEntity<String>  refundMoMo(@RequestBody Map<String, Object> request);
}
