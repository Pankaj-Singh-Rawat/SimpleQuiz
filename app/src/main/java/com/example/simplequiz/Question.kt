package com.example.simplequiz

data class Question(
    val text: String,
    val options: List<String>,
    val correctAnswer: String
)