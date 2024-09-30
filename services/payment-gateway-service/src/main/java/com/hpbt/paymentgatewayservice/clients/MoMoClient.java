package com.hpbt.paymentgatewayservice.clients;

import com.hpbt.paymentgatewayservice.dto.requests.MoMoRequest;
import com.hpbt.paymentgatewayservice.dto.responses.MoMoResponse;
import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

@FeignClient(name = "momo", url = "${momo.url.sandbox}")
public interface MoMoClient {
    @PostMapping("${momo.path.create}")
    MoMoResponse createMoMo(@RequestBody @Valid MoMoRequest moMoRequest);

    @PostMapping("${momo.path.query}")
    MoMoResponse queryMoMo(@RequestBody @Valid MoMoRequest moMoRequest);

    @PostMapping("${momo.path.confirm}")
    MoMoResponse confirmMoMo(@RequestBody @Valid MoMoRequest moMoRequest);

    @PostMapping("${momo.path.refund")
    MoMoResponse refundMoMo(@RequestBody @Valid MoMoRequest moMoRequest);
}
