package quang.lee.newszing.domain.usecases.news

import quang.lee.newszing.data.local.NewsDao
import quang.lee.newszing.domain.model.Article

class DeleteArticle(
    private val newsDao: NewsDao
) {
    suspend operator fun invoke(article: Article) {
        newsDao.delete(article)
    }
}