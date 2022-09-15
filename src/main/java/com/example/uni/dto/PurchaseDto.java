package com.example.uni.dto;


import com.example.uni.enums.PurchaseType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseDto {

    private Long id;
    private String visitTime; // 이것
    private PurchaseType type; // 승인 여부(불필요)
    private String userId; // 이것
    private Long bookId; // 이것
}
