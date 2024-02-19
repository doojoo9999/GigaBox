package com.teamsparta.gigabox.domain.coupon.repository

import com.teamsparta.gigabox.domain.coupon.model.CouponUsageEntity
import org.springframework.data.jpa.repository.JpaRepository

interface CouponUsageRepository : JpaRepository <CouponUsageEntity, Long> {
}