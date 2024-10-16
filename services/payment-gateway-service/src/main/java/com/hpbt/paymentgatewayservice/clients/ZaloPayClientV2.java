package com.hpbt.paymentgatewayservice.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "zalopayV2", url = "${zalopay.version2.url.sandbox}")
public interface ZaloPayClientV2 {
    @PostMapping("${zalopay.version2.path.create}")
    ResponseEntity<String> createZalopayV2(@RequestBody MultiValueMap<String, String> request);

    @PostMapping("${zalopay.version2.path.query}")
    ResponseEntity<String> queryZalopayV2(@RequestBody MultiValueMap<String, String> request);

    @PostMapping("${zalopay.version2.path.refund}")
    ResponseEntity<String> refundZalopayV2(@RequestBody MultiValueMap<String, String> request);

    @PostMapping("${zalopay.version2.path.query-refund}")
    ResponseEntity<String> queryRefundZalopayV2(@RequestBody MultiValueMap<String, String> request);
}
