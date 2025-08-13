package com.example.simplequiz

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    // Declare our views. The `lateinit` keyword promises that we'll initialize them later.
    private lateinit var questionTextView: TextView
    private lateinit var option1Button: Button
    private lateinit var option2Button: Button
    private lateinit var option3Button: Button
    private lateinit var scoreTextView: TextView

    // Game state variables
    private var currentQuestionIndex = 0
    private var score = 0

    // List of our quiz questions, created using our data class.
    private val questions = listOf(
        Question(
            "What is the capital of France?",
            listOf("Paris", "London", "Rome"),
            "Paris"
        ),
        Question(
            "Which planet is known as the Red Planet?",
            listOf("Earth", "Mars", "Jupiter"),
            "Mars"
        ),
        Question(
            "What is the largest ocean on Earth?",
            listOf("Atlantic", "Indian", "Pacific"),
            "Pacific"
        )
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize our views by finding them in the layout using their IDs.
        questionTextView = findViewById(R.id.questionTextView)
        option1Button = findViewById(R.id.option1Button)
        option2Button = findViewById(R.id.option2Button)
        option3Button = findViewById(R.id.option3Button)
        scoreTextView = findViewById(R.id.scoreTextView)

        // Set up the click listeners for our answer buttons.
        option1Button.setOnClickListener { checkAnswer(option1Button.text.toString()) }
        option2Button.setOnClickListener { checkAnswer(option2Button.text.toString()) }
        option3Button.setOnClickListener { checkAnswer(option3Button.text.toString()) }

        // Start the quiz by showing the first question.
        displayQuestion()
    }

    // Function to display the current question and its options.
    private fun displayQuestion() {
        if (currentQuestionIndex < questions.size) {
            val currentQuestion = questions[currentQuestionIndex]
            questionTextView.text = currentQuestion.text
            option1Button.text = currentQuestion.options[0]
            option2Button.text = currentQuestion.options[1]
            option3Button.text = currentQuestion.options[2]
            scoreTextView.text = "Score: $score"
        } else {
            // End of quiz, display the final score.
            questionTextView.text = "Quiz finished! Your final score is $score out of ${questions.size}."
            option1Button.visibility = Button.GONE
            option2Button.visibility = Button.GONE
            option3Button.visibility = Button.GONE
        }
    }

    // Function to check if the selected answer is correct.
    private fun checkAnswer(selectedAnswer: String) {
        val currentQuestion = questions[currentQuestionIndex]
        if (selectedAnswer == currentQuestion.correctAnswer) {
            score++
        }
        currentQuestionIndex++
        displayQuestion()
    }
}
