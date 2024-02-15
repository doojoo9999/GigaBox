package com.teamsparta.gigabox.domain.movie_info.service

import com.teamsparta.gigabox.domain.movie_info.dto.response.KeywordResponse
import com.teamsparta.gigabox.domain.movie_info.dto.response.SearchResponse
import com.teamsparta.gigabox.domain.movie_info.model.KeywordEntity
import com.teamsparta.gigabox.domain.movie_info.repository.JdbcRepository
import com.teamsparta.gigabox.domain.movie_info.repository.MovieInfoRepository
import org.springframework.data.domain.Page
import org.springframework.stereotype.Service
import org.springframework.data.domain.Pageable
import org.springframework.transaction.annotation.Transactional

@Service
class MovieInfoServiceImpl(
    private val movieInfoRepository: MovieInfoRepository,
//    private val keywordRepository: KeywordRepository,
    private val jdbcRepository: JdbcRepository
): MovieInfoService {
    @Transactional
    override fun searchByKeyword(
        keyword: String,
        pageable: Pageable
    ): Page<SearchResponse> {
        val myPage = movieInfoRepository.searchByKeyword(keyword, pageable)

        //제목만 가져온다.
        val titles = myPage.content.map { it.title }.toTypedArray()

        //키워드 엔티티를 리스트 형태로 가져온다.
        //해당하는 키워드가 없다면 당연히 리스트도 비어있다.
        val keywordEntityList = movieInfoRepository.findByTitles(titles)
        
        when{
            keywordEntityList.isEmpty() -> {
//                keywordRepository.saveAll(
//                    KeywordEntity.toEntities(titles)
//                )

                jdbcRepository.insertTitles(KeywordEntity.toEntities(titles))
            }
            keywordEntityList.size != titles.size -> {
                
                //DB에 저장되어 있는 제목을 가져옴
                val savedTitles = keywordEntityList.map { it.word }

                //이미 저장되어 있는 애들은 카운트를 1 늘려줌
                jdbcRepository.updateKeywordCount(savedTitles)

                //저장 안 된 제목을 얻어냄
                val notSavedTitles = titles.filterNot {
                    savedTitles.contains(it)
                }.toTypedArray()

                //저장 안 된 제목을 저장함
                jdbcRepository.insertTitles(KeywordEntity.toEntities(notSavedTitles))
            }
            else -> {
//                keywordEntityList.map {
//                    it.apply { increaseCount() }
//                }
                jdbcRepository.updateKeywordCount(titles.toList())
            }
        }

        return myPage
    }

    override fun getTopSearched(): List<KeywordResponse> {
        return movieInfoRepository
            .getTopSearched()
    }
}