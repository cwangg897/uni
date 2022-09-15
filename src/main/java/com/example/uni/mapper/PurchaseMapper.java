package com.example.uni.mapper;


import java.util.List;

import com.example.uni.dto.PurchaseDto;
import com.example.uni.enums.PurchaseType;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface PurchaseMapper {
    void save(@Param("p") PurchaseDto purchaseDto);

    void changeApprove(@Param("p") PurchaseDto purchaseDto, @Param("id") Long id);

    List<PurchaseDto> findAllByUserId(String userId);

    List<PurchaseDto> findAllByAdminByNotApprove(@Param("userId") String userId, @Param("type") PurchaseType notapprove);
}
