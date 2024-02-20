package com.teamsparta.gigabox.domain.coupon.service

import com.teamsparta.gigabox.domain.coupon.dto.request.GetCouponRequest
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import java.util.concurrent.CompletableFuture
import java.util.concurrent.Executors
import java.util.stream.Collectors


@SpringBootTest
class CouponServiceTest(@Autowired private val couponService: CouponService) {

    @Test
    fun testGetCommonCoupon() {
        val threadPool = Executors.newFixedThreadPool(100)

        val futures = (1..1000).map {
            CompletableFuture.supplyAsync({
                // getcouponrequest 불러오기
                val request = GetCouponRequest(couponNumber = "EVERYTHINGWILLBEOKAY")
                //서비스 돌리기
                couponService.getCommonCoupon(request)
            }, threadPool)
        }

        //요청이 끝날 때 까지 기다려
        CompletableFuture.allOf(*futures.toTypedArray()).join()

        val results = futures.stream().map { it.join() }.collect(Collectors.toList())

    }


}