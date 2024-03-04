package quang.lee.newszing.domain.usecases.news

import quang.lee.newszing.data.local.NewsDao
import quang.lee.newszing.domain.model.Article
import quang.lee.newszing.domain.repository.NewsRepository

class SelectArticle(private val newsRepository: NewsRepository) {
    suspend operator fun invoke(url: String): Article? {
        return newsRepository.selectArticle(url)
    }
}