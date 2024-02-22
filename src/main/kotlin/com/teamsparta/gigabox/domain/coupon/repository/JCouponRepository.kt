package com.teamsparta.gigabox.domain.coupon.repository

import com.teamsparta.gigabox.domain.coupon.model.CouponEntity

interface JCouponRepository {

    fun insertCoupons(coupons: List<CouponEntity>)

}