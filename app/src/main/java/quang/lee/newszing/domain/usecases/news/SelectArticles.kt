package quang.lee.newszing.domain.usecases.news

import kotlinx.coroutines.flow.Flow
import quang.lee.newszing.data.local.NewsDao
import quang.lee.newszing.domain.model.Article
import quang.lee.newszing.domain.repository.NewsRepository

class SelectArticles(private val newsRepository: NewsRepository) {

    operator fun invoke() : Flow<List<Article>> {
        return newsRepository.selectArticles()
    }
}