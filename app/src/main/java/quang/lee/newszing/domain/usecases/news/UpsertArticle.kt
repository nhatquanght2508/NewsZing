package quang.lee.newszing.domain.usecases.news

import quang.lee.newszing.data.local.NewsDao
import quang.lee.newszing.domain.model.Article

class UpsertArticle(
    private val newsDao: NewsDao
) {
    suspend operator fun invoke(article: Article) {
        newsDao.upsert(article)
    }
}