package com.teamsparta.gigabox.domain.theater.repository

import com.teamsparta.gigabox.domain.theater.model.TheaterEntity
import org.springframework.data.jpa.repository.JpaRepository

interface TheaterRepository : JpaRepository<TheaterEntity, Long> {
}