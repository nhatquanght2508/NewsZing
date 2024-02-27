package quang.lee.newszing.domain.usecases.news

import kotlinx.coroutines.flow.Flow
import quang.lee.newszing.data.local.NewsDao
import quang.lee.newszing.domain.model.Article

class GetArticles(private val newsDao : NewsDao) {

    operator fun invoke() : Flow<List<Article>> {
        return newsDao.getArticles()
    }
}