package quang.lee.newszing.presentation.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.compose.LazyPagingItems
import quang.lee.newszing.R
import quang.lee.newszing.domain.model.Article
import quang.lee.newszing.presentation.Dimens.Padding24
import quang.lee.newszing.presentation.common.ArticlesList
import quang.lee.newszing.presentation.common.SearchBar

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(
    articles: LazyPagingItems<Article>,
    navigateToSearch: () -> Unit,
    navigateToDetails: () -> Unit
) {
    val titles by remember {
        derivedStateOf {
            if (articles.itemCount > 10) {
                articles.itemSnapshotList.items
                    .slice(IntRange(start = 0, endInclusive = 9))
                    .joinToString(separator = " \uD83D\uDFE5 ") {
                        it.title
                    }
            } else ""
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = Padding24)
            .statusBarsPadding()
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_splash_new),
            contentDescription = null,
            modifier = Modifier
                .width(150.dp)
                .height(30.dp)
                .padding(horizontal = Padding24)
        )
        Spacer(modifier = Modifier.height(Padding24))
        SearchBar(
            text = "",
            readOnly = true,
            onValueChange = {},
            onSearch = {},
            onClick = {
                navigateToSearch
            }
        )
        Spacer(modifier = Modifier.height(Padding24))
        Text(
            text = titles,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = Padding24)
                .basicMarquee(),
            fontSize = 12.sp,
            color = colorResource(id = R.color.placeholder)
        )

        Spacer(modifier = Modifier.height(Padding24))
        ArticlesList(
            modifier = Modifier.padding(horizontal = Padding24),
            articles = articles,
            onClick = {
                navigateToDetails
            })
    }
}