package com.teamsparta.gigabox.domain.coupon.service

import com.teamsparta.gigabox.domain.coupon.dto.request.GetCouponRequest
import com.teamsparta.gigabox.infra.security.UserPrincipal

interface RedisCouponService {

    fun getCommonCoupon(request: GetCouponRequest)


}