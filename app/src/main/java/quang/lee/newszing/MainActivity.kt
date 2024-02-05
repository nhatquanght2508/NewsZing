package quang.lee.newszing

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import quang.lee.newszing.presentation.onboarding.OnBoardingScreen
import quang.lee.newszing.ui.theme.NewsZingTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window,false)
        installSplashScreen()
        setContent {
            NewsZingTheme {
                Box(modifier = Modifier.background(color = MaterialTheme.colorScheme.background))
                OnBoardingScreen()
            }
        }
    }
}