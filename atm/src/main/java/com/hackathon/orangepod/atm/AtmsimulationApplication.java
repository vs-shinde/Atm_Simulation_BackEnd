package com.hackathon.orangepod.atm;

import com.hackathon.orangepod.atm.service.impl.EmailService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAutoConfiguration
public class AtmsimulationApplication {

	public static void main(String[] args) {



		SpringApplication.run(AtmsimulationApplication.class, args);

//		EmailService sendEmailServiceImp = new EmailService();
//		sendEmailServiceImp.sendEmail("OTP for Loan app","Verification code\n" +
//				"Please use the verification code below to sign in.\n" +
//				"\n" +
//				"764909\n" +
//				"\n" +
//				"If you didn’t request this, you can ignore this email.\n" +
//				"\n" +
//				"Thanks,\n" +
//				"Loan Manager", "vishalbansode03@gmail.com",null);
	}

}
