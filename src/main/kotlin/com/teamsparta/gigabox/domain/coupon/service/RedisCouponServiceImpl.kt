package com.teamsparta.gigabox.domain.coupon.service

import com.teamsparta.gigabox.domain.coupon.dto.request.GetCouponRequest
import com.teamsparta.gigabox.domain.coupon.repository.RedisCouponRepository
import org.springframework.stereotype.Service

@Service
class RedisCouponServiceImpl(
    private val redisCouponRepository: RedisCouponRepository
) : RedisCouponService {
    override fun getCommonCoupon(request: GetCouponRequest) {
        val commonCoupon = redisCouponRepository.findByCouponNameOnRedis(request.couponNumber)

        println(commonCoupon)


//        commonCoupon?.apply {
//            if (available && couponCount > useCount) {
//                useCount++
//            } else {
//                throw IllegalStateException("사용할 수 없는 쿠폰입니다.")
//            }
//        } ?: throw IllegalStateException("쿠폰번호를 다시 확인해 주세요.")

//        redisCouponRepository.save(commonCoupon)

    }


}