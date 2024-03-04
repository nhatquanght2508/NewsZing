package quang.lee.newszing.presentation.navigator

import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.paging.compose.collectAsLazyPagingItems
import quang.lee.newszing.R
import quang.lee.newszing.domain.model.Article
import quang.lee.newszing.presentation.detail.DetailEvent
import quang.lee.newszing.presentation.detail.DetailViewModel
import quang.lee.newszing.presentation.detail.DetailsScreen
import quang.lee.newszing.presentation.favorite.FavoriteScreen
import quang.lee.newszing.presentation.favorite.FavoriteViewModel
import quang.lee.newszing.presentation.home.HomeScreen
import quang.lee.newszing.presentation.home.HomeViewModel
import quang.lee.newszing.presentation.navigator.components.BottomNavigationItem
import quang.lee.newszing.presentation.navigator.components.NewsBottomNavigation
import quang.lee.newszing.presentation.nvgraph.Route
import quang.lee.newszing.presentation.search.SearchScreen
import quang.lee.newszing.presentation.search.SearchViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsNavigator() {

    val bottomNavigationItems = remember {
        listOf(
            BottomNavigationItem(icon = R.drawable.ic_home, text = "Home"),
            BottomNavigationItem(icon = R.drawable.ic_search, text = "Search"),
            BottomNavigationItem(icon = R.drawable.ic_favorite, text = "Favorite")
        )
    }

    val navController = rememberNavController()
    val backStackState = navController.currentBackStackEntryAsState().value
    var selectedItem by rememberSaveable {
        mutableStateOf(0)
    }
    selectedItem = when (backStackState?.destination?.route) {
        Route.HomeScreen.route -> 0
        Route.SearchScreen.route -> 1
        Route.FavoriteScreen.route -> 2
        else -> 0
    }
    //Hide the bottom navigation when the user is in the details screen
    val isBottomVisible = remember(key1 = backStackState) {
        backStackState?.destination?.route == Route.HomeScreen.route ||
                backStackState?.destination?.route == Route.SearchScreen.route ||
                backStackState?.destination?.route == Route.FavoriteScreen.route
    }

    Scaffold(modifier = Modifier.fillMaxSize(), bottomBar = {
        if (isBottomVisible) {
            NewsBottomNavigation(
                items = bottomNavigationItems,
                selectedItem = selectedItem,
                onItemClick = { index ->
                    when (index) {
                        0 -> navigateToTab(
                            navController = navController,
                            route = Route.HomeScreen.route
                        )

                        1 -> navigateToTab(
                            navController = navController,
                            route = Route.SearchScreen.route
                        )

                        2 -> navigateToTab(
                            navController = navController,
                            route = Route.FavoriteScreen.route
                        )
                    }
                })
        }
    }) {
        val bottomPadding = it.calculateBottomPadding()
        NavHost(
            navController = navController,
            startDestination = Route.HomeScreen.route,
            modifier = Modifier.padding(bottom = bottomPadding)
        ) {
            composable(route = Route.HomeScreen.route) { backStackEntry ->
                val viewModel: HomeViewModel = hiltViewModel()
                val articles = viewModel.news.collectAsLazyPagingItems()
                HomeScreen(articles = articles,
                    navigateToSearch = {
                        navigateToTab(
                            navController = navController,
                            route = Route.SearchScreen.route
                        )
                    },
                    navigateToDetails = { article ->
                        navigateToDetail(
                            navController = navController,
                            article = article,
                        )
                    }
                )
            }
            composable(route = Route.SearchScreen.route) {
                val viewModel: SearchViewModel = hiltViewModel()
                val state = viewModel.state.value
                OnBackClickStateSaver(navController = navController)
                SearchScreen(
                    state = state,
                    event = viewModel::onEvent,
                    navigateToDetail = { article ->
                        navigateToDetail(
                            navController = navController,
                            article = article
                        )
                    }
                )
            }
            composable(route = Route.DetailsScreen.route) {
                val viewModel: DetailViewModel = hiltViewModel()
                if (viewModel.sideEffect != null) {
                    Toast.makeText(LocalContext.current, viewModel.sideEffect, Toast.LENGTH_SHORT).show()
                    viewModel.onEvent(DetailEvent.RemoveSideEffect)
                }
                navController.previousBackStackEntry?.savedStateHandle?.get<Article?>("article")
                    ?.let { article ->
                        DetailsScreen(
                            article = article, event = viewModel::onEvent,
                            navigateUp = {
                                navController.navigateUp()
                            }
                        )
                    }
            }
            composable(route = Route.FavoriteScreen.route) {
                val viewModel: FavoriteViewModel = hiltViewModel()
                val state = viewModel.state.value
                FavoriteScreen(state = state, navigateToDetail = { article ->
                    navigateToDetail(navController = navController, article = article)
                })
            }
        }
    }
}

@Composable
fun OnBackClickStateSaver(navController: NavController) {
    BackHandler(true) {
        navigateToTab(
            navController = navController,
            route = Route.HomeScreen.route
        )
    }
}

private fun navigateToTab(navController: NavController, route: String) {
    navController.navigate(route) {
        navController.graph.startDestinationRoute?.let { screen_route ->
            popUpTo(screen_route) {
                saveState = true
            }
        }
        launchSingleTop = true
        restoreState = true
    }
}

private fun navigateToDetail(navController: NavController, article: Article) {
    navController.currentBackStackEntry?.savedStateHandle?.set("article", article)
    navController.navigate(
        route = Route.DetailsScreen.route
    )
}