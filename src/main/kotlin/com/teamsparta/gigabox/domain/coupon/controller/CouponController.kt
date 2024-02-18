package com.teamsparta.gigabox.domain.coupon.controller

import com.teamsparta.gigabox.domain.coupon.dto.request.AddCommonCouponRequest
import com.teamsparta.gigabox.domain.coupon.dto.request.AddCouponRequest
import com.teamsparta.gigabox.domain.coupon.dto.request.GetCouponRequest
import com.teamsparta.gigabox.domain.coupon.dto.response.GetCouponResponse
import com.teamsparta.gigabox.domain.coupon.service.CouponService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/coupons")
class CouponController (
    private val couponService: CouponService
){

    @PostMapping("individual-coupon")
    fun createCoupons (
        @RequestBody request : AddCouponRequest
    ) : ResponseEntity <Unit> {

        couponService.createCoupons(request)

        return ResponseEntity
            .status(HttpStatus.OK)
            .build()
    }

    @PostMapping("/common-coupon")
    fun createCommonCoupons (
        @RequestBody request : AddCommonCouponRequest
    ) : ResponseEntity <Unit> {

        couponService.createCommonCoupons(request)

        return ResponseEntity
            .status(HttpStatus.OK)
            .build()

    }

    @PutMapping
    fun getCoupons (
        @RequestBody request : GetCouponRequest
    ) : ResponseEntity<GetCouponResponse> {

        return ResponseEntity
            .status(HttpStatus.OK)
            .body(couponService.getCoupon(request))
    }

}