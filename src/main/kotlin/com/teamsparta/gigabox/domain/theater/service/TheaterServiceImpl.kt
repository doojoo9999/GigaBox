package com.teamsparta.gigabox.domain.theater.service

import com.teamsparta.gigabox.domain.theater.dto.request.AddNewTheaterRequest
import com.teamsparta.gigabox.domain.theater.model.TheaterEntity
import com.teamsparta.gigabox.domain.theater.repository.TheaterRepository
import org.springframework.stereotype.Service

@Service
class TheaterServiceImpl(
    private val theaterRepository: TheaterRepository
) : TheaterService{
    override fun addNewTheater(request: AddNewTheaterRequest) {

        theaterRepository.save(TheaterEntity(
            seatCount = request.seatCount
        ))
    }


}