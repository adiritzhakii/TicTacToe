package com.efai.tictactoe

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private val board = Array(9) { "e" }
    private val strikes = arrayOf(
        intArrayOf(0, 1, 2), intArrayOf(3, 4, 5), intArrayOf(6, 7, 8),
        intArrayOf(0, 3, 6), intArrayOf(1, 4, 7), intArrayOf(2, 5, 8),
        intArrayOf(0, 4, 8), intArrayOf(2, 4, 6)
    )
    private val views = ArrayList<View>()
    private var currentTurn = "x"
    private var countOfTurns = 0
    private var markNumber = 0
    private lateinit var currentTurnImage: ImageView
    private lateinit var markImage: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        currentTurnImage = findViewById(R.id.currentTurn)
        markImage = findViewById(R.id.mark)
        setUpListeners()
        initBoard()
    }

    private fun initBoard() {
        board.fill("e")
    }

    private fun checkForWin(): Boolean {
        for (strike in strikes) {
            var count = 0
            for (position in strike) {
                if (board[position] == currentTurn) {
                    count++
                    if (count == 3) {
                        markNumber = strikes.indexOf(strike) + 1
                        return true
                    }
                }
            }
        }
        return false
    }

    private fun setUpListeners() {
        val ids = arrayOf(
            R.id.p0, R.id.p1, R.id.p2, R.id.p3, R.id.p4, R.id.p5, R.id.p6, R.id.p7, R.id.p8
        )
        ids.forEach { id ->
            val view = findViewById<ImageView>(id)
            views.add(view)
            view.setOnClickListener(this)
        }

    }

    private fun getXorOResource(): Int {
        return if (currentTurn == "x") R.drawable.x else R.drawable.o
    }

    private fun getCurrentResource(): Int {
        return if (currentTurn == "x") R.drawable.xplay else R.drawable.oplay
    }

    private fun getWinnerResource(): Int {
        return if (currentTurn == "x") R.drawable.xwin else R.drawable.owin
    }

    private fun getMarkResourceByNumber(): Int {
        return when (markNumber) {
            1 -> R.drawable.mark1
            2 -> R.drawable.mark2
            3 -> R.drawable.mark3
            4 -> R.drawable.mark4
            5 -> R.drawable.mark5
            6 -> R.drawable.mark6
            7 -> R.drawable.mark7
            8 -> R.drawable.mark8
            else -> 0
        }
    }

    private fun makeTurn(v: View, position: Int) {
        countOfTurns++
        v.isClickable = false
        v.setBackgroundResource(getXorOResource())
        board[position] = currentTurn
        val win = checkForWin()
        if (win || countOfTurns == 9) {
            stopGame(win)
        } else {
            currentTurn = if (currentTurn == "x") "o" else "x"
            currentTurnImage.setImageDrawable(ContextCompat.getDrawable(this, getCurrentResource()))
        }
    }

    private fun stopGame(win: Boolean) {
        if (countOfTurns == 9 && !win) {
            currentTurnImage.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.nowin))
        } else {
            currentTurnImage.setImageDrawable(ContextCompat.getDrawable(this, getWinnerResource()))
            markImage.setImageDrawable(ContextCompat.getDrawable(this, getMarkResourceByNumber()))
            markImage.bringToFront()
            views.forEach { it.isClickable = false }
        }
    }



    override fun onClick(v: View) {
        val position = (v.tag as String).toInt()
        makeTurn(v, position)
    }
}