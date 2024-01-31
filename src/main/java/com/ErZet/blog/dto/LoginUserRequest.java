package com.ErZet.blog.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Getter
@Setter
@Slf4j
@NoArgsConstructor
public class LoginUserRequest {
    @NotBlank(message="User name is required.")
    private String userName;
    @NotBlank(message="Password is required.")
    private String password;

    private int isSocialRegister;

    @Override
    public String toString() {
        return "LoginUserRequest{" +
                "userName='" + userName + '\'' +
                ", isSocialRegister=" + isSocialRegister +
                '}';
    }
}
