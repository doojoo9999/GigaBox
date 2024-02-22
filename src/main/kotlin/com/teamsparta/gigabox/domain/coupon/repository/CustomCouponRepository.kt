package com.teamsparta.gigabox.domain.coupon.repository

import com.teamsparta.gigabox.domain.coupon.model.CommonCouponEntity
import org.springframework.data.jpa.repository.JpaRepository

interface CustomCouponRepository {

    fun findByCouponNumberWithMember(couponNumber : String, memberId : Long) : CommonCouponEntity

}