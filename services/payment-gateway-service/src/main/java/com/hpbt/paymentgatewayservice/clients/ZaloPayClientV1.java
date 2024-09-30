package com.hpbt.paymentgatewayservice.clients;

import com.hpbt.paymentgatewayservice.dto.requests.ZaloPayRequest;
import com.hpbt.paymentgatewayservice.dto.responses.ZalopayResponse;
import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

@FeignClient(name = "zalopay", url = "${zalopay.version1.url.sandbox}")
public interface ZaloPayClientV1 {
//    @PostMapping("${zalopay.version1.path.create}")
//    ZalopayResponse createZalopayV1(@SpringQueryMap Map<String, Object> order);

    @PostMapping(value = "${zalopay.version1.path.create}")
    ResponseEntity<String> createZalopayV1(@RequestBody MultiValueMap<String, String> request);

    @PostMapping("${zalopay.version1.path.query}")
    ZalopayResponse queryZalopayV1(@RequestBody @Valid ZaloPayRequest request);

    @PostMapping("${zalopay.version1.path.refund}")
    ZalopayResponse refundZalopayV1(@RequestBody @Valid ZaloPayRequest request);

    @PostMapping("${zalopay.version1.path.query-refund}")
    ZalopayResponse queryRefundZalopayV1(@RequestBody @Valid ZaloPayRequest request);
}
