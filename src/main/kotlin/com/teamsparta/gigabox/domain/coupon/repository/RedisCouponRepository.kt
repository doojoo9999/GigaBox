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
        hashOps.put(coupon.couponNumber, "member_id", coupon.memberId?.id?:"")
        hashOps.put(coupon.couponNumber, "coupon_count", coupon.couponCount)
    }

    fun getCouponCount(couponNumber: String): Long {
        return hashOps.get(couponNumber, "coupon_count") as Long
    }

    fun getUseCount(couponNumber: String): Long {
        return hashOps.get(couponNumber, "use_count") as Long
    }

    fun incrementUseCount(couponNumber: String) {
        hashOps.increment(couponNumber, "use_count", 1L)
    }

    fun getMemberIdList(couponNumber: String): MutableList<Long> {
        return hashOps.get(couponNumber, "member_id") as MutableList<Long>
    }

    fun addMemberId(couponNumber: String, memberId: Long) {
        val memberIdList = getMemberIdList(couponNumber)
        memberIdList.add(memberId)
        hashOps.put(couponNumber, "member_id", memberIdList)
    }

}