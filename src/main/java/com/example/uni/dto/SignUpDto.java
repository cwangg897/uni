package com.example.uni.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@Builder
@AllArgsConstructor
public class SignUpDto {

    private String id;
    private String email;
    private String password;
    private String name;
    private String phoneNumber;
    private String studentId;
}
