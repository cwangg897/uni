package com.example.uni.service;


import java.util.List;

import com.example.uni.dto.PurchaseDto;
import com.example.uni.enums.BookStatus;
import com.example.uni.enums.PurchaseType;
import com.example.uni.mapper.PurchaseMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PurchaseService {

    private final PurchaseMapper purchaseMapper;
    private final BookService bookService;


    public void purchase(PurchaseDto purchaseDto) {
        purchaseMapper.save(purchaseDto);
    }

    // 구입목록에있는거 승인하면 책 상태도 바꿔주기
    @Transactional
    public void changeApprove(PurchaseDto purchaseDto, Long id) {
        purchaseMapper.changeApprove(purchaseDto, id);
        bookService.changeStatus(BookStatus.COMPLETE, purchaseDto.getBookId());
    }

    // 유저별 구입목록 보기
    public List<PurchaseDto> findAllByUserId(String userId) {
        return purchaseMapper.findAllByUserId(userId);
    }

    public List<PurchaseDto> findAllByAdminByNotApprove(String userId) {
        return purchaseMapper.findAllByAdminByNotApprove(userId, PurchaseType.NOTAPPROVE);
    }
}
