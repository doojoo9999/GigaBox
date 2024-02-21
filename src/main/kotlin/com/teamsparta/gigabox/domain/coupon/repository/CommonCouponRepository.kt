package com.teamsparta.gigabox.domain.coupon.repository

import com.teamsparta.gigabox.domain.coupon.model.CommonCouponEntity
import com.teamsparta.gigabox.domain.coupon.model.CouponEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface CommonCouponRepository : JpaRepository <CommonCouponEntity, Long> {
    fun findByCouponNumber(couponNumber : String) : CommonCouponEntity?

    @Modifying
    @Query("update CommonCouponEntity c set c.useCount = c.useCount + 1 where c.couponNumber = :couponNumber")
    fun incUseCount(@Param("couponNumber") couponNumber : String) : Int

}