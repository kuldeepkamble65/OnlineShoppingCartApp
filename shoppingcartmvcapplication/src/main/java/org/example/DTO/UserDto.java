package org.example.DTO;

import lombok.Data;

@Data
public class UserDto {

    private int userId;
    private String firstname;
    private String lastname;

    private  String email;
    private  String mobileNo;

}
