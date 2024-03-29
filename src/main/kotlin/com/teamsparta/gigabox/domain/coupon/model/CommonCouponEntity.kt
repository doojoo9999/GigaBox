package com.teamsparta.gigabox.domain.coupon.model

import com.teamsparta.gigabox.domain.coupon.dto.request.AddCommonCouponRequest
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
    var useCount : Int,

    @Column(name = "available")
    val available : Boolean,

//    @ElementCollection
//    @CollectionTable(name = "used_members", joinColumns = [JoinColumn(name = "id")])
//    @Column(name = "member_id_list")
//    var usedMembers : List<Long>,

    @JoinColumn(name = "member_id")
    @ManyToOne
    var memberId : MemberEntity?,

    @JoinColumn(name = "issued_by")
    @ManyToOne
    val issuedBy : MemberEntity,



) : BaseEntity() {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id : Long = 0L

    companion object {
        fun toEntity(request : AddCommonCouponRequest, issuedBy: MemberEntity) : CommonCouponEntity {

            return CommonCouponEntity(
                content = request.content,
                couponNumber = request.couponNumber,
                couponExp = request.couponExp,
                couponCount = request.couponCount,
                useCount = 0,
                available = true,
                memberId = null,
                issuedBy = issuedBy
            )

        }
    }
}