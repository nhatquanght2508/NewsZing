package quang.lee.newszing.domain.usecases.app_entry

import quang.lee.newszing.domain.usecases.app_entry.ReadAppEntry
import quang.lee.newszing.domain.usecases.app_entry.SaveAppEntry

data class AppEntryUseCases(
    val readAppEntry: ReadAppEntry,
    val saveAppEntry: SaveAppEntry
)
