package quang.lee.newszing.data.remote

import quang.lee.newszing.data.remote.dto.NewsResponse
import quang.lee.newszing.util.Constant.API_KEY
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {

    @GET("everything")
    suspend fun getNews(
        @Query("page") page: Int,
        @Query("sources") sources: String,
        @Query("apiKey") apiKey: String = API_KEY
    ): NewsResponse
}