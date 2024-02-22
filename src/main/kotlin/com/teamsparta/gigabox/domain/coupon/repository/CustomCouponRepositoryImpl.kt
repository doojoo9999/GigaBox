package com.teamsparta.gigabox.domain.coupon.repository

import com.querydsl.core.QueryFactory
import com.querydsl.jpa.impl.JPAQueryFactory
import com.teamsparta.gigabox.domain.coupon.model.CommonCouponEntity
import com.teamsparta.gigabox.domain.coupon.model.QCommonCouponEntity
import com.teamsparta.gigabox.domain.member.model.QMemberEntity
import com.teamsparta.gigabox.infra.querydsl.QueryDslSupport
import org.springframework.stereotype.Repository

@Repository
class CustomCouponRepositoryImpl : CustomCouponRepository, QueryDslSupport() {
    private val coupon = QCommonCouponEntity.commonCouponEntity
    private val member = QMemberEntity.memberEntity

    override fun findByCouponNumberWithMember(couponNumber: String, memberId: Long): CommonCouponEntity {
        return queryFactory
            .selectFrom(coupon)
            .join(coupon.memberId, member)
            .fetchJoin()
            .where(coupon.couponNumber.eq(couponNumber))
            .fetchOne() ?: throw IllegalArgumentException("쿠폰 번호가 잘못되었습니다.")

    }


}
