package quang.lee.newszing.data.remote.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import quang.lee.newszing.data.remote.NewsApi
import quang.lee.newszing.data.remote.NewsPagingSource
import quang.lee.newszing.domain.model.Article
import quang.lee.newszing.domain.repository.NewsRepository

class NewsRepositoryImp(
    private val newsApi: NewsApi
) : NewsRepository {
    override fun getNews(sources: List<String>): Flow<PagingData<Article>> {
        return Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = {
                NewsPagingSource(
                    newsApi = newsApi,
                    sources = sources.joinToString(separator = ",")
                )
            }
        ).flow
    }
}