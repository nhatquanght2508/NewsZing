package quang.lee.newszing.domain.usecases.news

import quang.lee.newszing.domain.model.Article
import quang.lee.newszing.domain.repository.NewsRepository

class UpsertArticle(
    private val newsRepository: NewsRepository
) {
    suspend operator fun invoke(article: Article) {
        newsRepository.upsertArticle(article)
    }
}