package quang.lee.newszing.presentation.onboarding

import androidx.annotation.DrawableRes
import quang.lee.newszing.R

data class Page(
    val title: String,
    val description: String,
    @DrawableRes val image: Int
)

val pages = listOf(
    Page(
        title = "Good News",
        description = "This is a good news",
        image = R.drawable.onboarding1
    ),
    Page(
        title = "Good News",
        description = "This is a good news",
        image = R.drawable.onboarding2
    ),
    Page(
        title = "Good News",
        description = "This is a good news",
        image = R.drawable.onboarding3
    )
)