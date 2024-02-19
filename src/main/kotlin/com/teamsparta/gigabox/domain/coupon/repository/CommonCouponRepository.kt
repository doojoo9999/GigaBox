package com.teamsparta.gigabox.domain.coupon.repository

import com.teamsparta.gigabox.domain.coupon.model.CommonCouponEntity
import org.springframework.data.jpa.repository.JpaRepository

interface CommonCouponRepository : JpaRepository <CommonCouponEntity, Long> {
}