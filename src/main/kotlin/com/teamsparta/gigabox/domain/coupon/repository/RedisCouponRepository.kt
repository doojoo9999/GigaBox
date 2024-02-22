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

    fun findByCouponNumberOnRedis(couponNumber : String) : Any? {
        return redisTemplate.opsForValue().get(couponNumber)
    }

    fun findByUseCountOnRedis(couponNumber : String) : Any? {
        return redisTemplate.opsForValue().get("{$couponNumber.useCount}")
    }

    fun incUseCountOnRedis(couponNumber : String) {
        redisTemplate.opsForSet().add("{$couponNumber.useCount}", 1)
    }


    fun save(coupon: CommonCouponEntity) {
        val hashOps = redisTemplate.opsForHash<String, Any>()
        hashOps.put(coupon.couponNumber, "useCount", coupon.useCount.toLong())
        hashOps.put(coupon.couponNumber, "memberId", coupon.memberId?.id?:"")

    }

    fun useCountSave(couponNumber : String ,useCount : Int) {
        redisTemplate.opsForValue().increment(couponNumber, useCount.toLong())
    }

}