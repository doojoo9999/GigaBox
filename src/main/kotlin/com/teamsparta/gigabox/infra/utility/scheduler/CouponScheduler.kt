package com.teamsparta.gigabox.infra.utility.scheduler

import com.teamsparta.gigabox.domain.coupon.model.CommonCouponEntity
import com.teamsparta.gigabox.domain.coupon.model.CouponEntity
import com.teamsparta.gigabox.domain.coupon.repository.CommonCouponRepository
import com.teamsparta.gigabox.domain.coupon.repository.RedisCouponRepository
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component

@Component
class CouponScheduler(
    private val commonCouponRepository : CommonCouponRepository,
    private val redisCouponRepository: RedisCouponRepository,
    private val redisTemplate: RedisTemplate<String, Any>
) : ApplicationRunner {
    override fun run(args: ApplicationArguments?) {
        //여기에 애플리케이션 시작할 때 뭐 할 건지 저장함

        val couponList = commonCouponRepository.findAllByAvailable(true)
        couponList.forEach {
            redisCouponRepository.save(it!!)
            redisTemplate.opsForValue().set("${it.couponNumber}.useCount", it.useCount)
            redisTemplate.opsForValue().set("${it.couponNumber}.memberId", {it.memberId})
        }
        }

    fun getAllAvailableCouponNumbers() : List<CommonCouponEntity?> {
        return commonCouponRepository.findAllByAvailable(true)
    }


    @Scheduled(fixedDelay = 60000) // 1분마다 작동시킴
    fun syncCoupons() {
        //쿠폰 번호를 가져오고
        val allAvailableCoupons = getAllAvailableCouponNumbers()

        // redis와 db를 동기화시킨다
        for (couponNumber in allAvailableCoupons) {
            val couponInRedis = redisCouponRepository.findByCouponNumber(couponNumber.toString())

            if (couponInRedis != null) {
                commonCouponRepository.save(couponInRedis)
            }
        }


    }



}