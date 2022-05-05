package com.borshevskiy.cleanarchitecturesingleactivitycompositionofnumber.domain.usecases

import com.borshevskiy.cleanarchitecturesingleactivitycompositionofnumber.domain.entity.Question
import com.borshevskiy.cleanarchitecturesingleactivitycompositionofnumber.domain.repository.GameRepository

class GenerateQuestionUseCase(private val repository: GameRepository) {

    operator fun invoke(maxSumValue: Int): Question {
        return repository.generateQuestion(maxSumValue, COUNT_OF_OPTIONS)
    }

    private companion object {
        private const val COUNT_OF_OPTIONS = 6
    }
}