package quang.lee.newszing.presentation.favorite

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import quang.lee.newszing.R
import quang.lee.newszing.domain.model.Article
import quang.lee.newszing.presentation.Dimens.Padding24
import quang.lee.newszing.presentation.common.ArticlesList
import javax.inject.Inject

@Composable
fun FavoriteScreen(
    state: FavoriteState,
    navigateToDetail: (Article) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .statusBarsPadding()
            .padding(top = Padding24, start = Padding24, end = Padding24)
    ) {
        Text(
            text = "Favorite",
            style = MaterialTheme.typography.displayMedium.copy(fontWeight = FontWeight.Bold),
            color = colorResource(id = R.color.text_title)
        )

        Spacer(modifier = Modifier.height(Padding24))

        ArticlesList(articles = state.articles, onClick = navigateToDetail)
    }
}