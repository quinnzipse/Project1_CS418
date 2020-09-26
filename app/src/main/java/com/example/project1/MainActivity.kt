package com.example.project1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private val highBound = 1000
    private val lowBound = 0
    private var leftButtonVal: Int = 0
    private var rightButtonVal: Int = 0
    private var points: Int = 0
    private var scoreTerm: String = ""

    private enum class Buttons {
        LEFT, RIGHT
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        scoreTerm = resources.getString(R.string.score_term)

        leftButtonVal = randomNumber()
        rightButtonVal = randomNumber()

        updatePointsText()
        updateButtonNumbers()
    }

    fun guess(view: View) {
        val correctButton: Buttons = if (rightButtonVal > leftButtonVal) Buttons.RIGHT else Buttons.LEFT
        val actualButton: Buttons = if (view.id == R.id.right_button) Buttons.RIGHT else Buttons.LEFT

        if (correctButton == actualButton) {
            Toast.makeText(this, "Correct!", Toast.LENGTH_SHORT).show()
            points++
        } else {
            Toast.makeText(this, "Incorrect!", Toast.LENGTH_SHORT).show()
            points--
        }

        updatePointsText()

        if(actualButton == Buttons.RIGHT) {
            do {
                rightButtonVal = randomNumber()
            } while(rightButtonVal == leftButtonVal)
        } else {
            do {
                leftButtonVal = randomNumber()
            } while (leftButtonVal == rightButtonVal)
        }

        updateButtonNumbers()
    }

    private fun updatePointsText() {
        val pointsMessage =  "$scoreTerm $points"

        points_text.text = pointsMessage
    }

    private fun updateButtonNumbers() {
        right_button.text = "$rightButtonVal"
        left_button.text = "$leftButtonVal"
    }

    private fun randomNumber(): Int {
        return (lowBound..highBound).random()
    }
}