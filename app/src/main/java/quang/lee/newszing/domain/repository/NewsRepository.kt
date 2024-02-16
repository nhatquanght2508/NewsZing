package quang.lee.newszing.domain.repository

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import quang.lee.newszing.domain.model.Article

interface NewsRepository {
    fun getNews(sources : List<String>) : Flow<PagingData<Article>>
}