package com.api4.payload;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegistrationDto {
    @Size(min = 3 , message = "min characters must be three")
    private String name;
    @Email
    private String email;
    @Size( min =10 , max =10 , message = "10 digits is must")
    private String mobile;

}
