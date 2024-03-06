package quang.lee.newszing.domain.usecases.news

import androidx.paging.PagingData
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import quang.lee.newszing.domain.model.Article
import quang.lee.newszing.domain.model.Source
import quang.lee.newszing.domain.repository.NewsRepository

class SelectArticlesTest {

    @MockK
    private lateinit var newsRepository: NewsRepository

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
    }

    @Test
    fun `invoke should be called`() = runTest {
        val expectedList = listOf(
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
        coEvery {
            newsRepository.selectArticles()
        } returns flowOf(expectedList)

        val selectArticles = SelectArticles(newsRepository)
        selectArticles.invoke().collect {listData ->
            assertEquals(expectedList, listData)
        }
    }
}
