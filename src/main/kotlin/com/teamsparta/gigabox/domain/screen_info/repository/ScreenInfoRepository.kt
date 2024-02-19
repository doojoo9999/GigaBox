package com.teamsparta.gigabox.domain.screen_info.repository

import com.teamsparta.gigabox.domain.screen_info.model.ScreenInfoEntity
import org.springframework.data.jpa.repository.JpaRepository

interface ScreenInfoRepository : JpaRepository<ScreenInfoEntity, Long> {
}