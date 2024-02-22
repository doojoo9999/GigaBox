package com.teamsparta.gigabox.domain.coupon.controller

import com.teamsparta.gigabox.domain.coupon.dto.request.GetCouponRequest
import com.teamsparta.gigabox.domain.coupon.service.RedisCouponService
import com.teamsparta.gigabox.infra.security.UserPrincipal
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Tag(name = "Coupons V2", description = "쿠폰 생성/등록 API V2")
@RestController
@RequestMapping("/api/v2/coupons")
class CouponControllerV2 (
    private val redisCouponService: RedisCouponService
){

    @Operation(summary = "공통 쿠폰 사용", description = "쿠폰 번호 입력이 필요합니다.")
    @PostMapping("/common-coupon/apply")
    fun getCommonCoupon(
        @RequestBody request : GetCouponRequest,
        @AuthenticationPrincipal userPrincipal: UserPrincipal
    ) : ResponseEntity<Unit> {

        redisCouponService.getCommonCoupon(request, userPrincipal)

        return ResponseEntity
            .status(HttpStatus.OK)
            .build()
    }

}