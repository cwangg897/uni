package com.example.uni.mapper;

import java.util.List;

import com.example.uni.dto.ReservationDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ReservationMapper {

    void save(@Param("reserve") ReservationDto reservationDto, @Param("userId") String userId);

    List<ReservationDto> getResponseAll(String userId);

    List<ReservationDto> getRequestAll(String userId);
}
