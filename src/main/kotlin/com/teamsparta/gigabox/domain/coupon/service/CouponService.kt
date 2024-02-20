package com.teamsparta.gigabox.domain.coupon.service

import com.teamsparta.gigabox.domain.coupon.dto.request.AddCommonCouponRequest
import com.teamsparta.gigabox.domain.coupon.dto.request.AddCouponRequest
import com.teamsparta.gigabox.domain.coupon.dto.request.GetCouponRequest
import com.teamsparta.gigabox.domain.coupon.dto.response.GetCouponResponse

interface CouponService {

    fun createCoupons(request : AddCouponRequest)

    fun getCoupon(request : GetCouponRequest) : GetCouponResponse

    fun createCommonCoupons(request : AddCommonCouponRequest)

    fun getCommonCoupon (request : GetCouponRequest) : GetCouponResponse

}