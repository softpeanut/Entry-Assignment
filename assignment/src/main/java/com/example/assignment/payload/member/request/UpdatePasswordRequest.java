package com.example.assignment.payload.member.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
public class UpdatePasswordRequest {

    @NotBlank
    private String password;

    @NotBlank
    private String newPassword;
}
