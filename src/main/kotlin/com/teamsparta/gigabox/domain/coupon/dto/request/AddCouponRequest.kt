package com.teamsparta.gigabox.domain.coupon.dto.request

data class AddCouponRequest (
    val content : String,
    val howManyMake : Int,
    val couponExp : Int,

)