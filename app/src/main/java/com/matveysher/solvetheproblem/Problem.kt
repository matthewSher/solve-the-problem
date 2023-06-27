package com.matveysher.solvetheproblem

class Problem(
    private val firstNumber: Int,
    private val secondNumber: Int,
    private val operation: String,
    ) {
    fun getAnswer() = when(operation) {
        "+" -> firstNumber + secondNumber
        else -> firstNumber - secondNumber
    }

    fun getProblemString() = "$firstNumber $operation $secondNumber = ?"

    fun generateIncorrectAnswer(indexOfShift: Int): Int {
        val answer = getAnswer()
        return ((answer - indexOfShift)..(answer + indexOfShift)).random()
    }
}