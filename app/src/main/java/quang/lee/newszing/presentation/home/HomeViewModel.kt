package quang.lee.newszing.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import quang.lee.newszing.domain.usecases.news.NewsUseCases
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(newsUseCases: NewsUseCases) : ViewModel() {
    val news = newsUseCases.getNews(
        sources = listOf("bbc-news","zing-mp3","zing-music")
    ).cachedIn(viewModelScope)
}