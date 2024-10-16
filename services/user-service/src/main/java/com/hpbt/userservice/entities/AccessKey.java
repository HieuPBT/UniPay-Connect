package com.hpbt.userservice.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

import static jakarta.persistence.EnumType.STRING;
import static jakarta.persistence.GenerationType.IDENTITY;

@RequiredArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
@Table(name= "access_key")
public class AccessKey {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "api_key", unique = true, nullable = false)
    @NotNull(message = "Api key cannot be null")
    @NotBlank(message = "Api key cannot be blank")
    private String apiKey;

    @Enumerated(STRING)
    @NotNull(message = "status cannot be null")
    @Column(name = "status", nullable = false)
    private Status status;

    @Column(name = "expires_at", nullable = false)
    @NotNull(message = "Expired time must be set")
    private LocalDateTime expires_at;

    @CreatedDate
    @Column(name = "created_at", updatable = false, nullable = false)
    private LocalDateTime created_at;

    @Column(name = "revoked_at")
    private LocalDateTime revoked_at;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", nullable = false, referencedColumnName = "id")
    @NotNull
    private User user;

}
