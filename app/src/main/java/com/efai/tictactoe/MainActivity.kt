package com.efai.tictactoe

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private val board = Array(9) { "e" }
    private var currentTurn = "x" // Define currentTurn to fix the error
    private lateinit var currentTurnImage: ImageView
    private lateinit var markImage: ImageView
    private lateinit var playAgain: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        markImage = findViewById(R.id.mark)
        initBoard()
    }

    private fun initBoard() {
        board.fill("e")
    }
}