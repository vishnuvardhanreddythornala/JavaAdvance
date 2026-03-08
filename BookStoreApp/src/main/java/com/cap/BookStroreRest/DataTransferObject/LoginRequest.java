package com.cap.BookStroreRest.DataTransferObject;

import lombok.Data;

@Data
public class LoginRequest {

    private String email;
    private String password;

}
