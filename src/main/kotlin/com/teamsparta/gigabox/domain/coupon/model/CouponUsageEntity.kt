package com.teamsparta.gigabox.domain.coupon.model

import com.teamsparta.gigabox.domain.member.model.MemberEntity
import jakarta.persistence.*

@Entity
@Table(name = "coupon_usage")
class CouponUsageEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id : Long = 0L,

    @ManyToOne
    @JoinColumn(name = "coupon_id")
    val coupon : CommonCouponEntity,

    @ManyToOne
    @JoinColumn(name = "member_id")
    val member : MemberEntity,

) {


}