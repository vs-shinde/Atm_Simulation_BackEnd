package com.hackathon.orangepod.atm.service.impl;

import com.hackathon.orangepod.atm.DTO.UpdatePinDto;
import com.hackathon.orangepod.atm.exceptions.AccountNotFoundException;
import com.hackathon.orangepod.atm.model.User;
import com.hackathon.orangepod.atm.model.UserOtp;
import com.hackathon.orangepod.atm.repository.OptRepository;
import com.hackathon.orangepod.atm.repository.UserRepository;
import com.hackathon.orangepod.atm.service.OtpService;
import com.hackathon.orangepod.atm.utils.AccountUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class OtpServiceImpl implements OtpService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private OptRepository optRepository;
    @Autowired
    private EmailService emailService;

    @Override
    public String generateAndSendOtp(Long userId) {        // Fetch the user details by the userid.
        Optional<User> userDetailsOptional = userRepository.findById(userId);
        if (userDetailsOptional.isEmpty()) {
            throw new AccountNotFoundException("User not found");
        }
        User userDetails = userDetailsOptional.get();        // Generate random OTP.
        Integer otp = AccountUtils.generateOtp();
        final String EMAIL_SUBJECT = "OTP Authentication required to pin change";
        final String EMAIL_MESSAGE = "Your one time password (OTP) to update the pin on ATM Simulation portal is " + otp + " Please do not share with Any one.";        //send email to the user with OTP.
        emailService.sendEmail(EMAIL_SUBJECT, EMAIL_MESSAGE, userDetails.getEmail(), null);
        userDetailsOptional.get().setOtp(otp);
        userRepository.save(userDetailsOptional.get());
        return "Otp sent to your Email id : " + userDetails.getEmail();
    }

    public boolean validateOTP( Integer otp , Long userId) {
        Optional<User> userDetailsOptional = userRepository.findById(userId);
        if (userDetailsOptional.isEmpty()) {
            throw new AccountNotFoundException("User not found");
        }
        return userDetailsOptional.get().getOtp() == otp;
        /*OTP otpEntity = otpRepository.findByEmailAndOtp(email, otp);
        if (otpEntity != null && otpEntity.getExpiryTime().isAfter(LocalDateTime.now())) {
            return true;
        }*/
    }

    public void updatePin(UpdatePinDto updatePinDTO) throws IllegalArgumentException {
        Optional<User> optionalUser = userRepository.findById(updatePinDTO.getUserId());
        if (optionalUser.isEmpty()) {
            throw new IllegalArgumentException("User not found");
        }
        User user = optionalUser.get();
        user.setPin(updatePinDTO.getNewPin());
        userRepository.save(user);
        String emailSubject = "ATM Pin Updated for your account";
        String emailMessage = "Your updated ATM Pin is: " + updatePinDTO.getNewPin() + " Note: Never share your ATM Pin with anyone.";
        emailService.sendEmail(emailSubject ,emailMessage,user.getEmail(),null );
    }

}
