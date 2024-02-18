package com.teamsparta.gigabox.domain.theater.service

import com.teamsparta.gigabox.domain.theater.dto.request.AddNewTheaterRequest

interface TheaterService {

    fun addNewTheater (request : AddNewTheaterRequest)

}