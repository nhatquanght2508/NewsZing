package quang.lee.newszing

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import quang.lee.newszing.domain.usecases.app_entry.AppEntryUseCases
import quang.lee.newszing.presentation.nvgraph.Route
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val appEntryUseCases: AppEntryUseCases
) : ViewModel() {

    var splashCondition by mutableStateOf(true)
    private set

    var startDestination by mutableStateOf(Route.AppStartNavigation.route)
    private set

    init {
        appEntryUseCases.readAppEntry().onEach { shouldStartFromDestination ->
            if (shouldStartFromDestination) {
                startDestination = Route.NewsNavigation.route
            } else {
                startDestination = Route.AppStartNavigation.route
            }
            delay(300)
            splashCondition = false
        }.launchIn(viewModelScope)
    }
}