package com.teamsparta.gigabox.domain.coupon.model

import com.teamsparta.gigabox.domain.member.model.MemberEntity
import com.teamsparta.gigabox.infra.auditing.BaseEntity
import jakarta.persistence.*


@Entity
@Table(name = "coupon")
class CouponEntity(

    @Column(name = "content")
    val content : String,

    @Column(name = "coupon_number")
    val couponNumber : String,

    @Column(name = "coupon_exp")
    val couponExp : Int,

    @JoinColumn(name = "issued_by")
    @ManyToOne
    val issuedBy : MemberEntity,

    @JoinColumn(name = "member_id")
    @ManyToOne
    val memberId : MemberEntity?,

    ): BaseEntity() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id : Long = 0L

//    @CreatedDate
//    @Column(name = "createcd_at", updatable = false)
//    val created_at : LocalDateTime
//
}