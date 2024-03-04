package quang.lee.newszing.domain.repository

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import quang.lee.newszing.domain.model.Article

interface NewsRepository {
    fun getNews(sources: List<String>): Flow<PagingData<Article>>
    fun searchNews(searchQuery: String, sources: List<String>): Flow<PagingData<Article>>

    suspend fun upsertArticle(article: Article)

    suspend fun deleteArticle(article: Article)

    fun selectArticles() : Flow<List<Article>>

    suspend fun selectArticle(url : String) : Article?
}