package com.teamsparta.gigabox.domain.movie_info.repository

import com.querydsl.core.BooleanBuilder
import com.querydsl.core.types.Expression
import com.querydsl.core.types.Order
import com.querydsl.core.types.OrderSpecifier
import com.querydsl.core.types.Projections
import com.querydsl.core.types.dsl.EntityPathBase
import com.querydsl.core.types.dsl.PathBuilder
import com.teamsparta.gigabox.domain.movie_info.dto.response.TopSearchResponse
import com.teamsparta.gigabox.domain.movie_info.dto.response.SearchResponse
import com.teamsparta.gigabox.domain.movie_info.model.KeywordEntity
import com.teamsparta.gigabox.domain.movie_info.model.QKeywordEntity
import com.teamsparta.gigabox.domain.movie_info.model.QMovieInfoEntity
import com.teamsparta.gigabox.infra.querydsl.QueryDslSupport
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Repository

@Repository
class MovieInfoRepositoryImpl: CustomRepository, QueryDslSupport() {
    private val movieInfo = QMovieInfoEntity.movieInfoEntity
    private val keywordEntity = QKeywordEntity.keywordEntity
    override fun searchByKeyword(
        keyword: String,
        pageable: Pageable
    ): Page<SearchResponse> {
        val whereClause = BooleanBuilder()
        whereClause.and(movieInfo.title.contains(keyword))

        val totalCount = queryFactory
            .select(movieInfo.count())
            .from(movieInfo)
            .where(whereClause)
            .fetchOne() ?: 0L

        val contents = queryFactory
            .select(
                Projections.constructor(
                    SearchResponse::class.java,
                    movieInfo.id,
                    movieInfo.title
                )
            )
            .from(movieInfo)
            .where(whereClause)
            .offset(pageable.offset)
            .limit(pageable.pageSize.toLong())
            .orderBy(*getOrderSpecifier(pageable, movieInfo))
            .fetch()

        return PageImpl(contents, pageable, totalCount)

    }
    private fun getOrderSpecifier(
        pageable: Pageable,
        path: EntityPathBase<*>
    ): Array<OrderSpecifier<*>>{
        val pathBuilder = PathBuilder(path.type, path.metadata)

        return pageable.sort.toList().map {
                order -> OrderSpecifier(
            if(order.isAscending) Order.ASC else Order.DESC,
            pathBuilder.get(order.property) as Expression<Comparable<*>>
        )
        }.toTypedArray()
    }

    override fun getTopSearched(): List<TopSearchResponse>{
        return queryFactory
            .select(
                Projections.constructor(
                    TopSearchResponse::class.java,
                    keywordEntity.word,
                    keywordEntity.count
                )
            )
            .from(keywordEntity)
            .orderBy(keywordEntity.count.desc(), keywordEntity.updatedAt.desc())
            .limit(10)
            .fetch()
    }

    override fun findByTitles(
        titles: Array<String>
    ): List<KeywordEntity> {
        return queryFactory
            .selectFrom(keywordEntity)
            .where(keywordEntity.word.`in`(*titles))
            .fetch()
    }
}