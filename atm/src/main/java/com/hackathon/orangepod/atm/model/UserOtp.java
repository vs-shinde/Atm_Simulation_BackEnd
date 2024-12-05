package com.hackathon.orangepod.atm.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "user_otp", schema = "public")
public class UserOtp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "otp", nullable = false, length = 6)
    private long otp;
    @OneToOne
    @MapsId
    @JoinColumn(name = "user_id")
    private User user;
    @Column(name = "otp_generated_date", nullable = false)
    private LocalDate otpGenerateDate;
    @Column(name = "generated_at", nullable = false)
    private LocalDateTime generatedAt;
    @Column(name = "expires_at", nullable = false)
    private LocalDateTime expiresAt;
}
