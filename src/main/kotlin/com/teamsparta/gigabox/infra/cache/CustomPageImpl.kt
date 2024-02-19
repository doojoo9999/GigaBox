package com.teamsparta.gigabox.infra.cache

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.JsonNode
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.PageRequest


@JsonIgnoreProperties(ignoreUnknown = true, value = ["pageable"])
class CustomPageImpl<T> : PageImpl<T> {
    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    constructor(
        @JsonProperty("content") content: List<T>,
        @JsonProperty("number") page: Int,
        @JsonProperty("size") size: Int,
        @JsonProperty("totalElements") total: Long
    ) : super(content, PageRequest.of(page, size), total)

    constructor(page: Page<T>) : super(page.content, page.pageable, page.totalElements)
}