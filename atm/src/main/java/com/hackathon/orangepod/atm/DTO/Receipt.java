package com.hackathon.orangepod.atm.DTO;

import lombok.Data;

@Data
public class Receipt {

    private Long userId;
    private Long amount;
    private String token;

}
