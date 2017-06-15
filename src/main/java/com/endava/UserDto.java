package com.endava;

import lombok.Data;

/**
 * Created by vsaban on 6/13/2017.
 */
@Data
public class UserDto {
    private String login;
    private String email;
    private String password;
    private String repeatPassword;
}
