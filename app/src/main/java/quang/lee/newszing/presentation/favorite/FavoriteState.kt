package quang.lee.newszing.presentation.favorite

import quang.lee.newszing.domain.model.Article

data class FavoriteState(
    val articles: List<Article> = emptyList()
)
