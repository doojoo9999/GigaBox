package com.teamsparta.gigabox.domain.coupon.repository

import com.teamsparta.gigabox.domain.coupon.model.CommonCouponEntity
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Repository

@Repository
class RedisCouponRepository(
    private val redisTemplate: RedisTemplate<String, Any>) {

    fun findByCouponNumber(couponNumber : String) : CommonCouponEntity? {
        return redisTemplate.opsForValue().get(couponNumber) as CommonCouponEntity
    }

    fun findByCouponNameOnRedis(couponNumber : String) : Any? {
        return redisTemplate.opsForValue().get(couponNumber)
    }


    fun save(coupon: CommonCouponEntity) {
        redisTemplate.opsForValue().set(coupon.couponNumber, coupon)
    }

    fun useCountSave(couponNumber : String ,useCount : Int) {
        redisTemplate.opsForValue().increment(couponNumber, useCount.toLong())
    }

}