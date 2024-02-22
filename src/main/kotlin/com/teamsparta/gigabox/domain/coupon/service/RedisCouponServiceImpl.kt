package com.teamsparta.gigabox.domain.coupon.service

import com.teamsparta.gigabox.domain.coupon.dto.request.GetCouponRequest
import com.teamsparta.gigabox.domain.coupon.repository.RedisCouponRepository
import org.springframework.stereotype.Service

@Service
class RedisCouponServiceImpl(
    private val redisCouponRepository: RedisCouponRepository
) : RedisCouponService {
    override fun getCommonCoupon(request: GetCouponRequest, ) {

        val couponCount = redisCouponRepository.getCouponCount(request.couponNumber)
        val useCount = redisCouponRepository.getUseCount(request.couponNumber)

        if (couponCount > useCount) {
            redisCouponRepository.incrementUseCount(request.couponNumber)
            redisCouponRepository.addMemberId(request.couponNumber, memberId)
        } else {
            throw IllegalStateException("사용할 수 없는 쿠폰입니다.")
        }





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