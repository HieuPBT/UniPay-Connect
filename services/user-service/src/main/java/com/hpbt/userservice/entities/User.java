package com.hpbt.userservice.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;
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
@Table(name= "users")
public class User {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "username", unique = true, nullable = false)
    @NotNull(message = "'username cannot be null")
    @Size(min = 1, max = 45)
    private String username;

    @JsonIgnore
    @Column(name = "password", nullable = false)
    @NotNull(message = "password cannot be null")
    @Size(min = 1, max = 100)
    private String password;

    @Email(message = "Must be a valid email")
    @NotNull(message = "Email cannot be null")
    @Column(name = "email")
    private String email;

    @NotNull(message = "Phone canot be null")
    @Pattern(regexp = "^\\+?[0-9. ()-]{7,25}$", message = "Invalid phone number")
    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "full_name", nullable = false)
    @NotNull(message = "full_name cannot be null")
    private String fullName;

    @Enumerated(STRING)
    @NotNull(message = "user must have a role")
    @Column(name = "user_role", nullable = false)
    private UserRole userRole;

    @Column(name = "avatar")
    private String avatar;

    @Column(name = "active")
    private Boolean active;

    @CreatedDate
    @Column(name = "created_at", updatable = false, nullable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at", insertable = false)
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    private Set<AccessKey> accessKeys;
}
