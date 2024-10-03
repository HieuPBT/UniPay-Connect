package com.hpbt.paymentgatewayservice.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PRIVATE;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Table(name = "payment_log")
@Entity
@FieldDefaults(level = PRIVATE)
public class PaymentLog {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id")
    Integer id;

    @Column(name = "request_url", nullable = false)
    @NotNull(message = "RequestUrl cannot be null")
    String requestUrl;

    @Column(name = "status")
    @NotNull(message = "Status cannot be null")
    @Enumerated(EnumType.STRING)
    Status status;

    @Column(name = "context")
    String context;

    @Column(name = "transaction_id", nullable = false)
    @NotNull(message = "TransactionID cannot be null")
    @NotBlank(message = "TransactionID cannot be blank")
    Integer transactionId;

    @CreatedDate
    @Column(name = "created_at", updatable = false)
    LocalDateTime createdAt;
}
