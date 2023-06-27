package com.matveysher.solvetheproblem

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.core.content.ContextCompat
import com.matveysher.solvetheproblem.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val signs: List<String> = listOf("+", "-")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val answerButtonsList: List<Button> = listOf(
            binding.answerButton1,
            binding.answerButton2,
            binding.answerButton3,
            binding.answerButton4,
        )

        var problem = generateProblem()
        var listOfAnswers = generateListOfAnswers(problem, answerButtonsList.size)
        setAnswerTextsForButtons(answerButtonsList, listOfAnswers)

        binding.problemTextView.text = problem.getProblemString()
        binding.nextProblemButton.isEnabled = false
        binding.nextProblemButton.setTextColor(ContextCompat.getColor(this, R.color.transparent_gray))

        binding.answerButton1.setOnClickListener {
            showTheCorrectAnswer(problem, answerButtonsList)
        }

        binding.answerButton2.setOnClickListener {
            showTheCorrectAnswer(problem, answerButtonsList)
        }

        binding.answerButton3.setOnClickListener {
            showTheCorrectAnswer(problem, answerButtonsList)
        }

        binding.answerButton4.setOnClickListener {
            showTheCorrectAnswer(problem, answerButtonsList)
        }

        binding.nextProblemButton.setOnClickListener {
            problem = generateProblem()
            listOfAnswers = generateListOfAnswers(problem, answerButtonsList.size)
            setAnswerTextsForButtons(answerButtonsList, listOfAnswers)

            binding.problemTextView.text = problem.getProblemString()

            answerButtonsList.forEach { button ->
                button.setBackgroundColor(ContextCompat.getColor(this@MainActivity, R.color.orange))
                button.isEnabled = true
            }

            it.isEnabled = false
            binding.nextProblemButton.setTextColor(ContextCompat.getColor(this, R.color.transparent_gray))
        }
    }

    private fun generateProblem() = Problem((0..100).random(), (0..100).random(), signs.random())

    private fun setAnswerTextsForButtons(listOfButtons: List<Button>, listOfAnswers: MutableList<Int>) {
        listOfButtons.forEach { button ->
            button.text = listOfAnswers.random().toString()
            listOfAnswers.remove((button.text as String).toInt())
        }
    }

    private fun generateListOfAnswers(problem: Problem, count: Int): MutableList<Int> {
        val listOfAnswers: MutableList<Int> = mutableListOf(problem.getAnswer())

        while (listOfAnswers.size != count) {
            val incorrectAnswer = problem.generateIncorrectAnswer(10)
            if (incorrectAnswer !in listOfAnswers) listOfAnswers.add(incorrectAnswer)
        }
        return listOfAnswers
    }

    private fun showTheCorrectAnswer(problem: Problem, listOfButtons: List<Button>) {
        listOfButtons.forEach { button ->
            if ((button.text as String).toInt() == problem.getAnswer()) {
                button.setBackgroundColor(ContextCompat.getColor(this, R.color.green))
                button.isEnabled = false
            } else {
                button.setBackgroundColor(ContextCompat.getColor(this, R.color.red))
                button.isEnabled = false
            }
        }
        binding.nextProblemButton.apply {
            isEnabled = true
            setTextColor(ContextCompat.getColor(this@MainActivity, R.color.gray))
        }
    }
}