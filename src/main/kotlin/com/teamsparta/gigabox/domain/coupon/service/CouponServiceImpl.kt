package com.teamsparta.gigabox.domain.coupon.service

import com.teamsparta.gigabox.domain.coupon.dto.request.AddCouponRequest
import com.teamsparta.gigabox.domain.coupon.model.CouponEntity
import com.teamsparta.gigabox.domain.coupon.repository.CouponRepository
import com.teamsparta.gigabox.domain.member.repository.MemberRepository
import com.teamsparta.gigabox.infra.utility.couponutility.CouponUtility
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CouponServiceImpl(
    private val couponRepository: CouponRepository,
    private val memberRepository: MemberRepository,
    private val couponUtility: CouponUtility
) : CouponService {

    @Transactional
    override fun createCoupons(request: AddCouponRequest) {
        val issuedBy = memberRepository.findByIdOrNull(/*userPrincipal.id*/1L)
            ?: throw IllegalArgumentException("Invalid Member")

        val coupons = mutableListOf<CouponEntity>()

        for (i in 0 .. request.howManyMake) {
            coupons.add(CouponEntity(
                content = request.content,
                couponNumber = couponUtility.createCouponNumber(),
                couponExp = request.couponExp,
                issuedBy = issuedBy, // 쿠폰 발급한 사람 나중에 security 적용되면 토큰 접속 정보 넣을 것
                memberId = issuedBy // 여기엔 쿠폰 사용한 사람이나 발급받은 사람 정보가 들어갈 거임
            ))
        }

        couponRepository.saveAll(coupons)

    }

}