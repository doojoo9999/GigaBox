package com.teamsparta.gigabox.domain.coupon.repository

import com.teamsparta.gigabox.domain.coupon.model.CouponEntity
import org.springframework.data.jpa.repository.JpaRepository

interface CouponRepository : JpaRepository<CouponEntity, Long>, JCouponRepository {

    fun findByContent(content : String) : CouponEntity

    fun findByCouponNumber(couponNumber : String) : CouponEntity
}