package com.teamsparta.gigabox.domain.coupon.dto.request

data class AddCommonCouponRequest(
    val content : String,
    val couponNumber : String,
    val couponExp : Int,
    val couponCount : Int,
)
