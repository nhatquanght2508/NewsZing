package quang.lee.newszing.domain.usecases

import kotlinx.coroutines.flow.Flow
import quang.lee.newszing.domain.manager.LocalUserManager

class ReadAppEntry(
    private val localUserManager: LocalUserManager
) {
    operator fun invoke() : Flow<Boolean> {
        return localUserManager.readAppEntry()
    }
}