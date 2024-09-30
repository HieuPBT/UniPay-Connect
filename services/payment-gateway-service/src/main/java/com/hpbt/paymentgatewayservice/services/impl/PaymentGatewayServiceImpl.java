package com.hpbt.paymentgatewayservice.services.impl;

import com.hpbt.paymentgatewayservice.clients.ZaloPayClientV2;
import com.hpbt.paymentgatewayservice.dto.requests.PaymentGatewayRequest;
import com.hpbt.paymentgatewayservice.dto.responses.MoMoResponse;
import com.hpbt.paymentgatewayservice.dto.responses.ZalopayResponse;
import com.hpbt.paymentgatewayservice.repositories.PaymentGatewayRepository;
import com.hpbt.paymentgatewayservice.services.PaymentGatewayService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PaymentGatewayServiceImpl implements PaymentGatewayService {
   final PaymentGatewayRepository paymentGatewayRepository;

   final ZaloPayClientV2 zaloPayClientV2;




    @Override
    public MoMoResponse createMoMo(PaymentGatewayRequest request) {
        return null;
    }

    @Override
    public ZalopayResponse zalopayCreateV2(PaymentGatewayRequest request) {
        final Map embed_data = new HashMap<>();
        embed_data.put("redirecturl", request.redirectUrl());

//        final Map[] item = {}

//        Map<String, Object> data = new HashMap<String, Object>(){
//            {
//                put("app_id", zalopayV2AppId);
//                put("app_trans_id", getCurrentTimeString("yyMMdd") + "_" + new Random().nextInt(1000000)); // mã giao dich có định dạng yyMMdd_xxxx
//                put("app_time", System.currentTimeMillis()); // miliseconds
//                put("app_user", "user123");
//                put("amount", request.amount());
//                put("description", "Export PDF Fee - " + request.amount());
//                put("bank_code", "zalopayapp");
//                put("item", request.item());
//                put("embed_data", request.embed_data());
//                put("callback_url", request.callbackUrl());
//            }
//        }

        return null;

    }
}
