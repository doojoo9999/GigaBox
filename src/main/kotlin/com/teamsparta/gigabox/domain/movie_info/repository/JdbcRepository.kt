package com.teamsparta.gigabox.domain.movie_info.repository

import com.teamsparta.gigabox.domain.movie_info.model.KeywordEntity
import org.springframework.jdbc.core.BatchPreparedStatementSetter
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Repository
import java.sql.PreparedStatement
import java.sql.Timestamp

@Repository
class JdbcRepository(
    private val jdbcTemplate: JdbcTemplate
) {
    fun insertTitles(
        titles: List<KeywordEntity>
    ){
        val sql = "INSERT INTO keyword (word, count, created_at, updated_at) VALUES (?, ?, ?, ?)"

        jdbcTemplate.batchUpdate(sql, object : BatchPreparedStatementSetter {
            override fun setValues(ps: PreparedStatement, i: Int) {
                ps.setString(1, titles[i].word)
                ps.setInt(2, titles[i].count)
                ps.setTimestamp(3, Timestamp.valueOf(titles[i].createdAt))
                ps.setTimestamp(4, Timestamp.valueOf(titles[i].updatedAt))
            }

            override fun getBatchSize(): Int {
                return titles.size
            }
        })
    }
}