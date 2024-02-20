package com.teamsparta.gigabox.domain.coupon.repository

import com.teamsparta.gigabox.domain.coupon.model.CouponEntity
import org.springframework.jdbc.core.BatchPreparedStatementSetter
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Repository
import java.sql.PreparedStatement
import java.sql.Timestamp

@Repository
class JCouponRepositoryImpl (
    private val jdbcTemplate : JdbcTemplate
) : JCouponRepository {
    override fun insertCoupons(
        coupons: List<CouponEntity>
    ) {
        val sql =
            "INSERT INTO coupon(available, content, coupon_count, coupon_exp, coupon_number, created_at, issued_by, member_id, updated_at) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)"

        jdbcTemplate.batchUpdate(sql, object : BatchPreparedStatementSetter {
            override fun setValues(ps: PreparedStatement, i: Int) {
                ps.setBoolean(1, coupons[i].available)
                ps.setString(2, coupons[i].content)
                ps.setInt(3, coupons[i].couponCount)
                ps.setInt(4, coupons[i].couponExp)
                ps.setString(5, coupons[i].couponNumber)
                ps.setTimestamp(6, Timestamp.valueOf(coupons[i].createdAt))
                ps.setLong(7, coupons[i].issuedBy.id)
                ps.setLong(8, coupons[i].memberId.id)
                ps.setTimestamp(9, Timestamp.valueOf(coupons[i].updatedAt))
            }

            override fun getBatchSize(): Int {
                return coupons.size
            }

        })

    }
}