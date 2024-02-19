package com.teamsparta.gigabox.domain.coupon.controller

import com.teamsparta.gigabox.domain.coupon.dto.request.AddCommonCouponRequest
import com.teamsparta.gigabox.domain.coupon.dto.request.AddCouponRequest
import com.teamsparta.gigabox.domain.coupon.dto.request.GetCouponRequest
import com.teamsparta.gigabox.domain.coupon.dto.response.GetCouponResponse
import com.teamsparta.gigabox.domain.coupon.service.CouponService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Tag(name = "Coupons", description = "쿠폰 생성/등록 API")
@RestController
@RequestMapping("/api/v1/coupons")
class CouponController (
    private val couponService: CouponService
){

    @Operation(summary = "개인 쿠폰 등록", description = "쿠폰 번호를 자동으로 생성하여 등록합니다.")
    @PostMapping("individual-coupon")
    fun createCoupons (
        @RequestBody request : AddCouponRequest
    ) : ResponseEntity <Unit> {

        couponService.createCoupons(request)

        return ResponseEntity
            .status(HttpStatus.OK)
            .build()
    }

    @Operation(summary = "공통 쿠폰 등록", description = "쿠폰 번호 입력이 필요합니다.")
    @PostMapping("/common-coupon")
    fun createCommonCoupons (
        @RequestBody request : AddCommonCouponRequest
    ) : ResponseEntity <Unit> {

        couponService.createCommonCoupons(request)

        return ResponseEntity
            .status(HttpStatus.OK)
            .build()

    }

    @Operation(summary = "개별 쿠폰 사용", description = "쿠폰 번호 입력이 필요합니다.")
    @PutMapping
    fun getCoupons (
        @RequestBody request : GetCouponRequest
    ) : ResponseEntity<GetCouponResponse> {

        return ResponseEntity
            .status(HttpStatus.OK)
            .body(couponService.getCoupon(request))
    }

    @Operation(summary = "공통 쿠폰 사용", description = "쿠폰 번호 입력이 필요합니다.")
    @PutMapping("/common-coupon")
    fun getCommonCoupon(
        @RequestBody request : GetCouponRequest
    ) : ResponseEntity<GetCouponResponse> {

        return ResponseEntity
            .status(HttpStatus.OK)
            .body(couponService.getCommonCoupon(request))
    }
}