package com.shaikhabdulgani.ConnectHub.dto;

import com.shaikhabdulgani.ConnectHub.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserWithTokenDto {
    private User user;
    private String token;
    private String refreshToken;
}
