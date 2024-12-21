package com.efai.tictactoe

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private val board = Array(9) { "e" }

    private val views = ArrayList<View>()
    private var currentTurn = "x"
    private var countOfTurns = 0
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


    private fun makeTurn(v: View, position: Int) {
        countOfTurns++
        v.isClickable = false
        v.setBackgroundResource(getXorOResource())
        board[position] = currentTurn
        currentTurn = if (currentTurn == "x") "o" else "x"
        currentTurnImage.setImageDrawable(ContextCompat.getDrawable(this, getCurrentResource()))

    }



    override fun onClick(v: View) {
        val position = (v.tag as String).toInt()
        makeTurn(v, position)
    }
}