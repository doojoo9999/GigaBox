package com.teamsparta.gigabox.domain.coupon.service

import com.teamsparta.gigabox.domain.coupon.dto.request.AddCouponRequest

interface CouponService {

    fun createCoupons(request : AddCouponRequest)

}