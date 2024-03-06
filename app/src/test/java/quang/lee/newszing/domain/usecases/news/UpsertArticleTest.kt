package quang.lee.newszing.domain.usecases.news

import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import quang.lee.newszing.domain.model.Article
import quang.lee.newszing.domain.model.Source
import quang.lee.newszing.domain.repository.NewsRepository

class UpsertArticleTest {

    @MockK
    private lateinit var newsRepository: NewsRepository

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
    }

    @Test
    fun `invoke should be call `() = runTest {
        val articles = Article(
            title = "abc",
            urlToImage = "abc",
            author = "abc",
            publishedAt = "abc",
            description = "abc",
            url = "abc",
            content = "abc",
            source = Source("1", "abc")
        )
        coEvery {
            newsRepository.upsertArticle(any())
        } returns Unit

        val upsertArticle = UpsertArticle(newsRepository)
        upsertArticle.invoke(articles)

        coVerify(exactly = 1) {
            newsRepository.upsertArticle(articles)
        }
    }
}