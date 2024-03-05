package quang.lee.newszing.presentation.detail

import quang.lee.newszing.domain.model.Article

sealed class DetailEvent {
    data class UpsertDeleteArticle(val article: Article) : DetailEvent()

    object RemoveSideEffect : DetailEvent()
}