package com.hackathon.orangepod.atm.controller;

import com.hackathon.orangepod.atm.DTO.UpdatePinDto;
import com.hackathon.orangepod.atm.service.OtpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:3000"})
@RestController
@RequestMapping("/atm/otp")
public class OtpController {
    @Autowired
    private OtpService otpService;

    @PostMapping("/generate")
    public ResponseEntity<String> generateAndSendOtp(@RequestParam(value = "userId") Long userId) {
        return ResponseEntity.ok(otpService.generateAndSendOtp(userId));
    }

    @PostMapping("/validate-otp")
    public String validateOTP(@RequestParam Long userId, @RequestParam Integer otp) {
        boolean isValid = otpService.validateOTP( otp ,userId);
        if (isValid) {
            return "OTP is valid.";
        } else {
            return "OTP is invalid or expired.";
        }
    }

    @PostMapping("/update-pin")
    public String updatePin(@RequestBody UpdatePinDto updatePinDTO) {
        try {
            otpService.updatePin(updatePinDTO);
            return "Pin updated successfully";
        } catch (IllegalArgumentException e) {
            return e.getMessage();
        }
    }


    }
