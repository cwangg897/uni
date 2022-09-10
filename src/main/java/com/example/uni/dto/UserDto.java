package com.example.uni.dto;


import com.example.uni.enums.UserType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@Builder
@AllArgsConstructor
public class UserDto {

    private String id;
    private String email;
    private String password;
    private String name;
    private String phoneNumber;
    private String studentId;
    private UserType type;
}
