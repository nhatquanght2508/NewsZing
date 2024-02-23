package quang.lee.newszing.presentation.nvgraph

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import androidx.paging.compose.collectAsLazyPagingItems
import quang.lee.newszing.presentation.home.HomeScreen
import quang.lee.newszing.presentation.home.HomeViewModel
import quang.lee.newszing.presentation.onboarding.OnBoardingScreen
import quang.lee.newszing.presentation.onboarding.OnBoardingViewModel
import quang.lee.newszing.presentation.search.SearchScreen
import quang.lee.newszing.presentation.search.SearchViewModel

@Composable
fun NavGraph(
    startDestination: String
) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = startDestination) {
        navigation(
            route = Route.AppStartNavigation.route,
            startDestination = Route.OnBoardingScreen.route
        ) {
            composable(
                route = Route.OnBoardingScreen.route
            ) {
                val viewModel: OnBoardingViewModel = hiltViewModel()
                OnBoardingScreen(event = {
                    viewModel.onEvent(it)
                })
            }
        }

        navigation(
            route = Route.NewsNavigation.route,
            startDestination = Route.NewsNavigatorScreen.route
        ) {
            composable(
                route = Route.NewsNavigatorScreen.route
            ) {
                val searchViewModel : SearchViewModel = hiltViewModel()
                SearchScreen(state = searchViewModel.state.value, event = searchViewModel::onEvent, navigate = {})
            }
        }
    }
}