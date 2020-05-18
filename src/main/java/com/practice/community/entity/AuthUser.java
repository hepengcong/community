package com.practice.community.entity;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class AuthUser {
    @NotBlank
    private String username;

    @NotBlank
    private String password;
}
