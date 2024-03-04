package quang.lee.newszing.presentation.detail

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import quang.lee.newszing.domain.model.Article
import quang.lee.newszing.domain.usecases.news.NewsUseCases
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val newsUseCases: NewsUseCases
) : ViewModel() {
    var sideEffect by mutableStateOf<String?>(null)
    private set

    fun onEvent(event : DetailEvent) {
        when(event) {
            is DetailEvent.UpsertDeleteArticle -> {
                viewModelScope.launch {
                    val article = newsUseCases.selectArticle(event.article.url)
                    if (article == null) {
                        upsertArticle(event.article)
                    } else {
                        deleteArticle(event.article)
                    }
                }
            }
            is DetailEvent.RemoveSideEffect -> {
                sideEffect = null
            }
        }
    }

    private suspend fun upsertArticle(article: Article) {
        newsUseCases.upsertArticle(article)
        sideEffect = "Upsert article"
    }

    private suspend fun deleteArticle(article: Article) {
        newsUseCases.deleteArticle(article)
        sideEffect = "Deleted article"
    }
}