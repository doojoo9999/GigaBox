package com.teamsparta.gigabox.domain.coupon.service

import com.teamsparta.gigabox.domain.coupon.dto.request.GetCouponRequest

interface RedisCouponService {

    fun getCommonCoupon(request: GetCouponRequest)


}