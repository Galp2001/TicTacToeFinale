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

        isPlayerX=!isPlayerX


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

