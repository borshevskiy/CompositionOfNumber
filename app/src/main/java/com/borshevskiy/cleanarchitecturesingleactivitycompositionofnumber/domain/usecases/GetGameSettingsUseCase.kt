package com.borshevskiy.cleanarchitecturesingleactivitycompositionofnumber.domain.usecases

import com.borshevskiy.cleanarchitecturesingleactivitycompositionofnumber.domain.entity.GameSettings
import com.borshevskiy.cleanarchitecturesingleactivitycompositionofnumber.domain.entity.Level
import com.borshevskiy.cleanarchitecturesingleactivitycompositionofnumber.domain.repository.GameRepository

class GetGameSettingsUseCase(private val repository: GameRepository) {

    operator fun invoke(level: Level): GameSettings {
        return repository.getGameSettings(level)
    }
}
