package com.teamsparta.gigabox.domain.movie_info.model

enum class RATINGS(val description: String) {
    ALL("전체관람가"),
    R12("12세 이상 관람가"),
    R15("15세 이상 관람가"),
    R18("청소년 관람불가"),
    RESTRICT("제한상영가")
}