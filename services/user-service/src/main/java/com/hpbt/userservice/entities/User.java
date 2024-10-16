package com.hpbt.userservice.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import static jakarta.persistence.EnumType.STRING;
import static jakarta.persistence.GenerationType.IDENTITY;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name= "users")
public class User {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id")
    Integer id;

    @Column(name = "username", unique = true, nullable = false)
    @Size(min = 3, message = "Username must be at least 3 character")
    @NotNull(message = "Username cannot be null")
    @NotBlank(message = "Username cannot be blank")
    String username;

    @JsonIgnore
    @Column(name = "password", nullable = false)
    @NotNull(message = "password cannot be null")
    @NotBlank(message = "Password cannot be blank")
    @Pattern(
            regexp = "^(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?])[A-Za-z\\d!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?]{8,100}$",
            message = "Password must be 8-100 characters long, contain at least 1 uppercase letter, 1 number, and 1 special character"
    )
    @Size(min = 8, max = 100)
    String password;

    @Email(message = "Must be a valid email")
    @NotBlank(message = "Email cannot be blank")
    @NotNull(message = "Email cannot be null")
    @Column(name = "email")
    String email;

    @Pattern(
            regexp = "^(\\+\\d{1,3}[- ]?)?\\d{10}$",
            message = "Phone number must be valid (e.g. +1234567890 or 1234567890)"
    )
    @NotBlank(message = "Phone number cannot be blank")
    @NotNull(message = "Phone number cannot be null")
    @Column(name = "phone_number")
    String phoneNumber;

    @Column(name = "full_name", nullable = false)
    @NotNull(message = "Full name cannot be null")
    @NotBlank(message = "Full name cannot be blank")
    @Size(min = 6, message = "Full name must be at least 6 character")
    String fullName;

    @Enumerated(STRING)
    @NotNull(message = "user must have a role")
    @Column(name = "user_role", nullable = false)
    UserRole userRole;

    @Column(name = "avatar")
    String avatar;

    @Column(name = "active")
    Boolean active;

    @CreatedDate
    @Column(name = "created_at", updatable = false, nullable = false)
    LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at", insertable = false)
    LocalDateTime updatedAt;

    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    Set<AccessKey> accessKeys;
}
