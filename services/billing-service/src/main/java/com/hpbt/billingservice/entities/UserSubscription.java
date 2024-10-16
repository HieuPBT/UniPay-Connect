package com.hpbt.billingservice.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;

@Entity
@Table(name = "user_subsription")
@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
public class UserSubscription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "user_id")
    Long userId;

    @ManyToOne
    @JoinColumn(name = "subscription_tier_id", nullable = false)
    SubscriptionTier subscriptionTier;

    @CreationTimestamp
    @Column(name = "start_date", nullable = false)
    Instant startDate;

    @Column(name = "end_date", nullable = false)
    Instant endDate;
}
