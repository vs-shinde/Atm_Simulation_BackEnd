package com.hackathon.orangepod.atm.repository;

import com.hackathon.orangepod.atm.model.UserOtp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OptRepository extends JpaRepository<UserOtp, Long> {
}
