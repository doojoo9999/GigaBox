package com.teamsparta.gigabox.domain.coupon.service

import com.teamsparta.gigabox.domain.coupon.dto.request.AddCommonCouponRequest
import com.teamsparta.gigabox.domain.coupon.dto.request.AddCouponRequest
import com.teamsparta.gigabox.domain.coupon.dto.request.GetCouponRequest
import com.teamsparta.gigabox.domain.coupon.dto.response.GetCouponResponse
import com.teamsparta.gigabox.domain.coupon.model.CommonCouponEntity
import com.teamsparta.gigabox.domain.coupon.model.CouponEntity
import com.teamsparta.gigabox.domain.coupon.repository.CommonCouponRepository
import com.teamsparta.gigabox.domain.coupon.repository.CouponRepository
import com.teamsparta.gigabox.domain.member.repository.MemberRepository
import com.teamsparta.gigabox.infra.aop.StopWatch
import com.teamsparta.gigabox.infra.utility.couponutility.CouponUtility
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CouponServiceImpl(
    private val couponRepository: CouponRepository,
    private val memberRepository: MemberRepository,
    private val couponUtility: CouponUtility,
    private val commonCouponRepository : CommonCouponRepository,
) : CouponService {

    @Transactional
    @StopWatch
    override fun createCoupons(request: AddCouponRequest) {
        val issuedBy = memberRepository.findByIdOrNull(/*userPrincipal.id*/1L)
            ?: throw IllegalArgumentException("Invalid Member")

        val coupons = mutableListOf<CouponEntity>()

        for (i in 0..request.couponCount) {
            coupons.add(
                CouponEntity(
                    content = request.content,
                    couponNumber = couponUtility.createCouponNumber(),
                    couponExp = request.couponExp,
                    couponCount = request.couponCount,
                    issuedBy = issuedBy, // 쿠폰 발급한 사람 나중에 security 적용되면 토큰 접속 정보 넣을 것
                    memberId = issuedBy, // 여기엔 쿠폰 사용한 사람이나 발급받은 사람 정보가 들어갈 거임
                    available = true
                )
            )
        }

        couponRepository.insertCoupons(coupons)

//        couponRepository.saveAll(coupons)
    }

    override fun getCoupon(request: GetCouponRequest): GetCouponResponse {
        val userCheck = memberRepository.findByIdOrNull(/*userPrincipal.id*/ 2L)
            ?: throw IllegalArgumentException("Invalid Member")

        val couponCheck = couponRepository.findByCouponNumber(request.couponNumber)
            ?: throw IllegalArgumentException("Invalid Coupon")


        if (couponCheck.couponNumber == request.couponNumber && couponCheck.available) {

            couponCheck.available = false
            couponCheck.memberId = userCheck

            couponRepository.save(couponCheck)

            return GetCouponResponse(couponNumber = couponCheck.couponNumber)
        } else {
            throw IllegalArgumentException("이미 사용된 쿠폰이거나 쿠폰 번호가 유요하지 않습니다.")
        }
    }

    override fun createCommonCoupons(request: AddCommonCouponRequest) {
        val issuedBy = memberRepository.findByIdOrNull(/*userPrincipal.id*/1L)
            ?: throw IllegalArgumentException("Invalid Member")

        val commonCoupon = CommonCouponEntity(
            content = request.content,
            couponNumber = request.couponNumber,
            couponExp = request.couponExp,
            couponCount = request.couponCount,
            useCount = 0,
            memberId = null,
            issuedBy = issuedBy,
            available = true,
//            usedMembers = emptyList()
        )

        commonCouponRepository.save(commonCoupon)
    }

    @Transactional
    override fun getCommonCoupon(request: GetCouponRequest) {

        val commonCoupon = commonCouponRepository.findByCouponNumberWithMember(request.couponNumber, 2L)

        commonCoupon.apply {
            if (available && couponCount > useCount) {

                commonCouponRepository.save(commonCoupon)


//                commonCouponRepository.incUseCount(request.couponNumber)
            } else {
                throw IllegalArgumentException("사용할 수 없는 쿠폰입니다")
            }
        }

        commonCouponRepository.save(commonCoupon)


//      ----------------------------------------------------------------------------------------------
//        val userCheck = memberRepository.findByIdOrNull(/*userPrincipal.id*/ 2L)
//            ?: throw IllegalArgumentException("Invalid Member")
//
//        val couponCheck = commonCouponRepository.findByCouponNumber(request.couponNumber)?.apply {
//
//            if (available && couponCount > useCount) {
//                memberId = userCheck
////                useCount += 1
//                commonCouponRepository.incUseCount(request.couponNumber) // useCount 증가를 db에서 직접 증가시킴
//                // 근데 이렇게 하면 오히려 300+ 개수만큼이 발급되지 않을까
//            } else {
//                throw IllegalArgumentException("사용할 수 없는 쿠폰입니다.")
//            }
//        } ?: throw IllegalArgumentException("쿠폰 번호가 틀렸습니다.")
//
//        commonCouponRepository.save(couponCheck)
//
//        return GetCouponResponse(couponNumber = couponCheck.couponNumber)

//        --------------------------------------------------------------------------------------------
        //        if (couponCheck.available && couponCheck.couponCount >= couponCheck.useCount) {
//
//        }
//
//        couponCheck.memberId = userCheck
//        couponCheck.useCount += 1


    }
}