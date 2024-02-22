package com.teamsparta.gigabox.domain.coupon.repository

import com.teamsparta.gigabox.domain.coupon.model.CommonCouponEntity
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Repository

@Repository
class RedisCouponRepository(
    private val redisTemplate: RedisTemplate<String, Any>) {

    private val hashOps = redisTemplate.opsForHash<String, Any>()

    fun save(coupon: CommonCouponEntity) {
        val hashOps = redisTemplate.opsForHash<String, Any>()
        hashOps.put(coupon.couponNumber, "use_count", coupon.useCount.toLong())
        hashOps.put(coupon.couponNumber, "coupon_count", coupon.couponCount)
    }

    fun getCouponCount(couponNumber: String): Int {
        return hashOps.get(couponNumber, "coupon_count") as Int
    }

    fun getUseCount(couponNumber: String): Int {
        return hashOps.get(couponNumber, "use_count") as Int
    }

    fun incrementUseCount(couponNumber: String) {
        hashOps.increment(couponNumber, "use_count", 1)
    }
}
