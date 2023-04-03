package com.example.prack15

import android.annotation.SuppressLint
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    private lateinit var trueButton: Button
    private lateinit var falseButton: Button
    private lateinit var nextButton: Button
    private lateinit var questionsText: TextView
    private lateinit var backButton: Button
    private val questionBank = listOf(
            Question(R.string.questions_one, true),
            Question(R.string.questions_two, true),
            Question(R.string.questions_free, false),
            Question(R.string.questions_four, true))
    private var currentIndex = 0

    private fun updateQuestion()
    {
        val questionTextResId = questionBank[currentIndex].textResId
        questionsText.setText(questionTextResId)
    }

    private fun checkAnswer(userAnswer: Boolean)
    {
        val correctAnswer = questionBank[currentIndex].answer

        val messageResId= if(userAnswer == correctAnswer){
            R.string.corrected
        }else{
            R.string.Incorrected }
        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        backButton = findViewById(R.id.back_button)
        nextButton = findViewById(R.id.next_button)
        falseButton = findViewById(R.id.button_false)
        trueButton = findViewById(R.id.button_true)
        questionsText = findViewById(R.id.questinos_text)

        questionsText.setOnClickListener {
            currentIndex = (currentIndex + 1) % questionBank.size
            updateQuestion()
        }
        nextButton.setOnClickListener{
            currentIndex = (currentIndex + 1) % questionBank.size
            updateQuestion()
        }
        backButton.setOnClickListener {
            if(currentIndex > 0) {
                currentIndex = (currentIndex - 1) % questionBank.size
            }
            else {
                currentIndex = 3
            }
            updateQuestion()
        }
        updateQuestion()

        trueButton.setOnClickListener {
            checkAnswer(true)
        }
        falseButton.setOnClickListener{
            checkAnswer(false)
        }
    }
}