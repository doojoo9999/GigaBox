package com.teamsparta.gigabox.domain.coupon.service

import com.teamsparta.gigabox.domain.coupon.dto.request.GetCouponRequest
import com.teamsparta.gigabox.domain.coupon.model.CommonCouponEntity
import com.teamsparta.gigabox.domain.coupon.repository.CommonCouponRepository
import com.teamsparta.gigabox.domain.member.model.MemberEntity
import com.teamsparta.gigabox.domain.member.repository.MemberRepository
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.data.repository.findByIdOrNull
import org.springframework.test.context.junit.jupiter.SpringExtension


@SpringBootTest
@ExtendWith(SpringExtension::class)
class CouponServiceTest {

//    @Autowired
//    private lateinit var couponService: CouponService
//    @Test
//    fun testGetCommonCoupon() {
//        val threadPool = Executors.newFixedThreadPool(100)
//
//        val futures = (1..100).map {
//            CompletableFuture.supplyAsync({
//                // getcouponrequest 불러오기
//                val request = GetCouponRequest(couponNumber = "testcoupon")
//                //서비스 돌리기
//                couponService.getCommonCoupon(request)
//            }, threadPool)
//        }
//
//        //요청이 끝날 때 까지 기다려
//        CompletableFuture.allOf(*futures.toTypedArray()).join()
//
//        val results = futures.stream().map { it.join() }.collect(Collectors.toList())
//
//    }


    @Autowired
    private lateinit var couponService : CouponService

    @MockBean
    private lateinit var memberRepository : MemberRepository

    @MockBean
    private lateinit var commonCouponRepository: CommonCouponRepository

    @Test
    fun `getCommonCoupon 이 정상적으로 작동했을 때`() {
        //given
        val request = GetCouponRequest(couponNumber = "testcoupon")
        val member = mockk<MemberEntity>()
        val coupon = mockk<CommonCouponEntity> {
            every {available} returns (true)
            every {couponCount} returns 300
            every {useCount} returns 0
        }

        every { memberRepository.findByIdOrNull(eq(2L))} returns member
        every { commonCouponRepository.findByCouponNumber(coupon.couponNumber) } returns coupon

        // when
        val response = couponService.getCommonCoupon(request)

        // then
        assertEquals(request.couponNumber, response.couponNumber)
        verify { commonCouponRepository.incUseCount(request.couponNumber) }
        verify { commonCouponRepository.save(coupon) }

    }

}