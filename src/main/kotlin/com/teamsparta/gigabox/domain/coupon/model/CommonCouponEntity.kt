package com.teamsparta.gigabox.domain.coupon.model

import com.teamsparta.gigabox.domain.member.model.MemberEntity
import com.teamsparta.gigabox.infra.auditing.BaseEntity
import jakarta.persistence.*

@Entity
@Table (name = "common_coupon")
class CommonCouponEntity (

    @Column(name = "content")
    val content : String,

    @Column(name = "coupon_number")
    val couponNumber : String,

    @Column(name = "coupon_exp")
    val couponExp : Int,

    @Column(name = "coupon_count")
    val couponCount : Int,

    @Column(name = "use_count")
    val useCount : Int,

    @JoinColumn(name = "member_id")
    @ManyToOne
    val memberId : MemberEntity,

    @JoinColumn(name = "issued_by")
    @ManyToOne
    val issuedBy : MemberEntity,



) : BaseEntity() {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id : Long = 0L
}