package quang.lee.newszing.domain.usecases.news

import androidx.paging.PagingData
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import quang.lee.newszing.domain.model.Article
import quang.lee.newszing.domain.model.Source
import quang.lee.newszing.domain.repository.NewsRepository

@RunWith(MockitoJUnitRunner::class)
class GetNewsTest {

    @MockK
    lateinit var newsRepository: NewsRepository

    lateinit var getNewsUseCase: GetNews

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        getNewsUseCase = GetNews(newsRepository)
    }

    @Test
    fun `invoke should return Flow of PagingData`() = runTest {
        val sources = listOf("bbc-news", "abc-news")
        val expectedPagingData = PagingData.from(
            listOf(
                Article(
                    title = "abc",
                    urlToImage = "abc",
                    author = "abc",
                    publishedAt = "abc",
                    description = "abc",
                    url = "abc",
                    content = "abc",
                    source = Source("1", "abc")
                ),
                Article(
                    title = "abc2",
                    urlToImage = "abc2",
                    author = "abc2",
                    publishedAt = "abc2",
                    description = "abc2",
                    url = "abc2",
                    content = "abc2",
                    source = Source("2", "abc2")
                ),
            )
        )
        coEvery {
            newsRepository.getNews(sources)
        } returns flowOf(expectedPagingData)
        GetNews(newsRepository).invoke(sources = sources).collect {pagingData ->
            flowOf(pagingData).collect{ data ->
                assertEquals(expectedPagingData, data)
            }
        }
    }
}