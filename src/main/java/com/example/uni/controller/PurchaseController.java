package com.example.uni.controller;


import java.util.List;

import com.example.uni.annotation.LoginCheck;
import com.example.uni.annotation.SessionUserId;
import com.example.uni.dto.PurchaseDto;
import com.example.uni.enums.UserType;
import com.example.uni.service.PurchaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/purchases")
public class PurchaseController {

    private final PurchaseService purchaseService;

    // 구입 하다
    @LoginCheck(userType = UserType.USER)
    @PostMapping
    public ResponseEntity<Void> purchase(@RequestBody PurchaseDto purchaseDto) {
        // 어떤책구입하는지 적혀있으니 그거가지고 구입목록을 만드는것이다
        purchaseService.purchase(purchaseDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    // 승인하다 조교만
    @LoginCheck(userType = UserType.ADMIN)
    @PatchMapping("/{id}")
    public ResponseEntity<Void> changeApprove(@PathVariable Long id,
                                              @RequestBody PurchaseDto purchaseDto) {
        purchaseService.changeApprove(purchaseDto, id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    // 구입 목록 조회하다 유저별
    @LoginCheck(userType = UserType.USER)
    @GetMapping
    public ResponseEntity<List<PurchaseDto>> findAll(@SessionUserId String userId) {
        return ResponseEntity.status(HttpStatus.OK).body(purchaseService.findAllByUserId(userId));
    }

    // 승인되지않는 목록 조회 조교만 필요한거임
    @LoginCheck(userType = UserType.ADMIN)
    @GetMapping("/type/approve")
    public ResponseEntity<List<PurchaseDto>> findAllByAdminByNotApprove(@SessionUserId String userId) { // 조교아이디
        return ResponseEntity.status(HttpStatus.OK).body(purchaseService.findAllByAdminByNotApprove(userId));
    }

    // 승인된 목록 조회


}
