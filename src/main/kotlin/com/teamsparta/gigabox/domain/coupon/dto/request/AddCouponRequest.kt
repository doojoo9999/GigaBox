package com.teamsparta.gigabox.domain.coupon.dto.request

data class AddCouponRequest (
    val content : String,
    val couponCount : Int,
    val couponExp : Int,
)