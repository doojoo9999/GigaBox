package com.teamsparta.gigabox.domain.coupon.repository

import com.teamsparta.gigabox.domain.coupon.model.CommonCouponEntity
import com.teamsparta.gigabox.domain.coupon.model.CouponEntity
import org.springframework.data.jpa.repository.JpaRepository

interface CommonCouponRepository : JpaRepository <CommonCouponEntity, Long> {
    fun findByCouponNumber(couponNumber : String) : CommonCouponEntity?

}