package com.hpbt.paymentgatewayservice.clients;

import com.hpbt.paymentgatewayservice.dto.requests.FindTransactionByOrderIdRequest;
import com.hpbt.paymentgatewayservice.dto.requests.TransactionRequest;
import com.hpbt.paymentgatewayservice.dto.requests.UpdateTransactionStatusRequest;
import com.hpbt.paymentgatewayservice.dto.responses.ApiResponse;
import com.hpbt.paymentgatewayservice.dto.responses.TransactionResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "transaction-service", url = "${feign.url.transaction-service}")
public interface TransactionServiceClient {
    @PostMapping("/create-transaction")
    ResponseEntity<ApiResponse<TransactionResponse>> createTransaction(@RequestBody TransactionRequest request);

    @PostMapping("/update-transaction")
    ResponseEntity<ApiResponse<TransactionResponse>> upddateTransaction(@RequestBody UpdateTransactionStatusRequest request);

    @PostMapping("/get-transaction-by-orderId")
    ResponseEntity<ApiResponse<TransactionResponse>> findTransactionByOrderId(@RequestBody FindTransactionByOrderIdRequest request);

    
}
