package com.hpbt.paymentgatewayservice.mappers;

import com.hpbt.paymentgatewayservice.dto.requests.PaymentGatewayRequest;
import com.hpbt.paymentgatewayservice.dto.responses.PaymentGatewayResponse;
import com.hpbt.paymentgatewayservice.dto.responses.PaymentLogResponse;
import com.hpbt.paymentgatewayservice.entities.PaymentLog;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class PaymentLogMapper {

    public PaymentLog toPaymentLog(PaymentGatewayRequest request){

        return PaymentLog.builder()
                .requestUrl(request.requestUrl())
                .status(request.status())
                .context(request.context())
                .transactionId(request.transactionId())
                .createdAt(LocalDateTime.now())
                .build();
    }

    public PaymentLogResponse toPayLogResponse(PaymentLog paymentLog){

        return new PaymentLogResponse(
                paymentLog.getId(),
                paymentLog.getRequestUrl(),
                paymentLog.getStatus(),
                paymentLog.getContext(),
                paymentLog.getTransactionId(),
                paymentLog.getCreatedAt()
        );
    }
}
