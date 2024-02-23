package quang.lee.newszing.data.remote.dto

import quang.lee.newszing.domain.model.Article

data class NewsResponse(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)