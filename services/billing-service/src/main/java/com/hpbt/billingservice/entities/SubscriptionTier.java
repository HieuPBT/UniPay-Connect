package com.hpbt.billingservice.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;

@Entity
@Table(name = "subscription_tier")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SubscriptionTier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", unique = true, nullable = false)
    private String name;

    @Column(name = "max_requests", nullable = false)
    private Integer maxRequests;

    @Column(name = "price", nullable = false)
    private Double price;

    @Column(name = "created_at", updatable = false)
    @CreationTimestamp
    Instant createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    Instant updatedAt;
}
