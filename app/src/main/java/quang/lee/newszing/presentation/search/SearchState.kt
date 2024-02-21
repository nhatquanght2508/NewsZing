package quang.lee.newszing.presentation.search

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import quang.lee.newszing.domain.model.Article

data class SearchState(
    val searchQuery: String = "",
    val article : Flow<PagingData<Article>>? = null
)
