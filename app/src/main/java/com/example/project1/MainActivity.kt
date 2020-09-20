package com.example.project1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.random.Random
import kotlin.random.nextUInt

class MainActivity : AppCompatActivity() {
    private var leftButtonVal: Int = 0
    private var rightButtonVal: Int = 0
    private var points: Int = 0
    private var random: Random = Random(654185826358848)
    private var scoreTerm: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        scoreTerm = resources.getString(R.string.score_term)

        updatePointsText()
        generateNewNumbers()
    }

    fun guess(view: View) {
        val correctButton: String = if (rightButtonVal > leftButtonVal) "right" else "left"
        val actualButton: String = if (view.id == R.id.right_button) "right" else "left"

        if (correctButton == actualButton) {
            Toast.makeText(this, "Correct!", Toast.LENGTH_SHORT).show()
            points++
        } else {
            Toast.makeText(this, "Incorrect!", Toast.LENGTH_SHORT).show()
            points--
        }

        updatePointsText()

        generateNewNumbers()
    }

    private fun updatePointsText() {
        val pointsMessage =  "$scoreTerm $points"

        points_text.text = pointsMessage
    }

    private fun generateNewNumbers() {
        do {
            leftButtonVal = random.nextInt(1000)
            rightButtonVal = random.nextInt(1000)
        } while (leftButtonVal == rightButtonVal)


        left_button.text = "$leftButtonVal"
        right_button.text = "$rightButtonVal"
    }
}