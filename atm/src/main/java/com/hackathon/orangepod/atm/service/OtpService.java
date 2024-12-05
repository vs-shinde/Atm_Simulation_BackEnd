package com.hackathon.orangepod.atm.service;

import com.hackathon.orangepod.atm.DTO.UpdatePinDto;

public interface OtpService {
    String generateAndSendOtp(Long userId);
    public boolean validateOTP( Integer otp , Long userId);
    public void updatePin(UpdatePinDto updatePinDTO) throws IllegalArgumentException ;
}