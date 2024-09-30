package com.hpbt.paymentgatewayservice.clients;

import com.hpbt.paymentgatewayservice.dto.requests.ZaloPayRequest;
import com.hpbt.paymentgatewayservice.dto.responses.ZalopayResponse;
import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

@FeignClient(name = "zalopayV2", url = "${zalopay.version2.url.sandbox}")
public interface ZaloPayClientV2 {
    @PostMapping("${zalopay.version2.path.create}")
    ResponseEntity<String> createZalopayV2(@RequestBody MultiValueMap<String, String> request);

    @PostMapping("${zalopay.version2.path.query}")
    ZalopayResponse queryZalopayV2(@RequestBody @Valid ZaloPayRequest request);

    @PostMapping("${zalopay.version2.path.refund}")
    ZalopayResponse refundZalopayV2(@RequestBody @Valid ZaloPayRequest request);

    @PostMapping("${zalopay.version2.path.query-refund}")
    ZalopayResponse queryRefundZalopayV2(@RequestBody @Valid ZaloPayRequest request);
}
