package quang.lee.newszing.presentation.search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.paging.compose.collectAsLazyPagingItems
import quang.lee.newszing.domain.model.Article
import quang.lee.newszing.presentation.Dimens.Padding24
import quang.lee.newszing.presentation.common.ArticlesList
import quang.lee.newszing.presentation.common.SearchBar

@Composable
fun SearchScreen(
    state: SearchState,
    event: (SearchEvent) -> Unit,
    navigateToDetail: (Article) -> Unit
) {
    Column(
        modifier = Modifier
            .padding(top = Padding24, start = Padding24, end = Padding24)
            .statusBarsPadding()
    ) {
        SearchBar(text = state.searchQuery, readOnly = false, onValueChange = {
            event(SearchEvent.UpdateSearchQuery(it))
        },
            onSearch = {
                event(SearchEvent.SearchNews)
            }
        )
        Spacer(modifier = Modifier.height(Padding24))
        state.article?.let {
            val article = it.collectAsLazyPagingItems()
            ArticlesList(
                articles = article,
                onClick = { navigateToDetail(it) }
            )
        }
    }
}