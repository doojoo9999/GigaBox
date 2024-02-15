package com.teamsparta.gigabox.domain.member.repository

import com.teamsparta.gigabox.domain.member.model.MemberEntity
import org.springframework.data.jpa.repository.JpaRepository

interface MemberRepository : JpaRepository <MemberEntity, Long>{



}