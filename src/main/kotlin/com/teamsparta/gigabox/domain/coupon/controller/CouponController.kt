package com.teamsparta.gigabox.domain.coupon.controller

import com.teamsparta.gigabox.domain.coupon.dto.request.AddCouponRequest
import com.teamsparta.gigabox.domain.coupon.service.CouponService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/coupons")
class CouponController (
    private val couponService: CouponService
){

    @PostMapping
    fun createCoupons (
        @RequestBody request : AddCouponRequest
    ) : ResponseEntity <Unit> {

        couponService.createCoupons(request)

        return ResponseEntity
            .status(HttpStatus.OK)
            .build()

    }

}