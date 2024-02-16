package com.teamsparta.gigabox.domain.reservation.repository

import com.teamsparta.gigabox.domain.reservation.model.ReservationEntity
import org.springframework.data.jpa.repository.JpaRepository

interface reservationRepository : JpaRepository<ReservationEntity, Long> {


}