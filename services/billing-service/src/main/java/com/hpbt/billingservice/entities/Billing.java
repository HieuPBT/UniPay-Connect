package com.hpbt.billingservice.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "billing")
@NoArgsConstructor
@AllArgsConstructor
public class Billing {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne
    @JoinColumn(name = "user_subscription_id", nullable = false)
    UserSubscription userSubscription;

    @Column(name = "request_count", nullable = false)
    Integer requestCount;

    @Column(name = "amount", nullable = false)
    Double amount;

    @Column(name = "billing_period", nullable = false)
    String billingPeriod;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    BillingStatus status;
}
