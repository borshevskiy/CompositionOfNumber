package com.borshevskiy.cleanarchitecturesingleactivitycompositionofnumber.domain.repository

import com.borshevskiy.cleanarchitecturesingleactivitycompositionofnumber.domain.entity.GameSettings
import com.borshevskiy.cleanarchitecturesingleactivitycompositionofnumber.domain.entity.Level
import com.borshevskiy.cleanarchitecturesingleactivitycompositionofnumber.domain.entity.Question

interface GameRepository {

    fun generateQuestion(maxSumValue: Int, countOfOptions: Int): Question

    fun getGameSettings(level: Level): GameSettings

}