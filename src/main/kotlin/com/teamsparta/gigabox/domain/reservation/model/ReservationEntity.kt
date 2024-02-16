package com.teamsparta.gigabox.domain.reservation.model

import com.teamsparta.gigabox.domain.member.model.MemberEntity
import com.teamsparta.gigabox.domain.theater.model.TheaterEntity
import jakarta.persistence.*

@Entity
@Table(name = "reservation")
class ReservationEntity(
    @Column(name = "seat_num")
    val seatNum : Int,

    @JoinColumn(name = "member_id")
    @ManyToOne
    val member : MemberEntity,

    @JoinColumn(name = "theater_id")
    @ManyToOne
    val theater : TheaterEntity,

) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id : Long = 0L
}