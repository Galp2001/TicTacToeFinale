package com.example.tictactoefinale

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private var isPlayerX=true
    private var gameActive=true
    private lateinit var statusText: TextView
    private lateinit var buttons: List<Button>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        statusText=findViewById(R.id.Header_textView)
        buttons=listOf(findViewById(R.id.btn1),findViewById(R.id.btn2),findViewById(R.id.btn3),
            findViewById(R.id.btn4),findViewById(R.id.btn5),findViewById(R.id.btn6),
            findViewById(R.id.btn7),findViewById(R.id.btn8),findViewById(R.id.btn9))
        for(button in buttons){
            button.setOnClickListener{
                onCellClicked(button)
            }
        }

        findViewById<Button>(R.id.Retry_button).setOnClickListener{
            resetGame()
        }


    }


    private fun onCellClicked(button: Button) {
        if(button.text!="" || !gameActive) return

        if(isPlayerX) {
            button.text = "X"
            statusText.text="Player O's turn"
        }
        else{
            button.text="O"
            statusText.text="Player X's turn"
        }

        checkWin()
        // Only switch player if the game is still active (no win or tie)
        if (gameActive) {
            isPlayerX=!isPlayerX
        }


    }

    private fun checkWin(){
        val winningPositions = arrayOf(
            intArrayOf(0, 1, 2), intArrayOf(3, 4, 5), intArrayOf(6, 7, 8), // Rows
            intArrayOf(0, 3, 6), intArrayOf(1, 4, 7), intArrayOf(2, 5, 8), // Cols
            intArrayOf(0, 4, 8), intArrayOf(2, 4, 6)                       // Diagonals
        )
        for (position in winningPositions) {
            val (a, b, c) = position
            if (buttons[a].text.isNotEmpty()&&buttons[a].text == buttons[b].text && buttons[a].text == buttons[c].text) {
                gameActive=false
                val winner=buttons[a].text
                statusText.text="Player $winner has won!"
                return
            }
        }

        // If no winner and no empty cells, it's a tie
        val anyEmpty = buttons.any { it.text.isEmpty() }
        if (!anyEmpty) {
            gameActive = false
            statusText.text = "It's a tie!"
        }
    }

    private fun resetGame() {
        isPlayerX=true
        gameActive=true
        statusText.text="Player X's turn"
        for(button in buttons){
            button.text=""
        }

    }

}
