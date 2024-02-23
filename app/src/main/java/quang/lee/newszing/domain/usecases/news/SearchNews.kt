package quang.lee.newszing.domain.usecases.news

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import quang.lee.newszing.domain.model.Article
import quang.lee.newszing.domain.repository.NewsRepository

class SearchNews(private val newsRepository: NewsRepository) {

    operator fun invoke(searchQuery: String, sources: List<String>): Flow<PagingData<Article>> {
        return newsRepository.searchNews(searchQuery = searchQuery, sources = sources)
    }
}