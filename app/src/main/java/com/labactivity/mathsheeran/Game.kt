package com.labactivity.mathsheeran

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Toast
import com.labactivity.mathsheeran.Result
import com.labactivity.mathsheeran.databinding.ActivityGameBinding
import java.util.*
import kotlin.concurrent.timer

class Game : AppCompatActivity() {
    private lateinit var binding: ActivityGameBinding
    private lateinit var timer: CountDownTimer
    private var randInt = Random()
    private var timeIsRunning = true
    private var initialCountDown:Long = 20000
    private var countDownInterval:Long = 1000
    private var num1 = 0
    private var num2 = 0
    private var userAns = 0
    private var correctAns = 0
    private var score = 0
    private var life = 3
    private var acceptAns = true
    private var mode:String? = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGameBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mode = intent.getStringExtra("mode")

        gameContinue(mode)

        binding.okBtn.setOnClickListener(){
            if(acceptAns){
                //Validate if user inputs its answer
                while (true){
                    try {
                        userAns = binding.answerEdtTxt.text.toString().toInt()
                        break
                    } catch (e:Exception){
                        return@setOnClickListener
                    }
                }
                //Compare user answer to the correct answer
                if(userAns == correctAns){
                    binding.questionTxt.text = "Your answer is correct!"
                    score += 10
                    binding.scoreTxt.text = "$score"
                } else {
                    binding.questionTxt.text = "Your answer is incorrect!"
                    life -= 1
                    binding.lifeTxt.text = "$life"
                }
                binding.answerEdtTxt.setText("")
                pauseTimer()
                acceptAns = false
            }
        }

        binding.nextBtn.setOnClickListener(){
            acceptAns = true
            resetTimer()
            gameContinue(mode)
        }

    }
    private fun gameContinue(mode:String?){
        if (life == 0){
            val intent = Intent(applicationContext, Result::class.java)
            intent.putExtra("mode", mode)
            intent.putExtra("score", score)
            startActivity(intent)
            this.finish()
        } else {
            num1 = randInt.nextInt(100)
            num2 = randInt.nextInt(100)

            if (mode == "Addition"){
                binding.edIV.setImageResource(R.drawable.edplus)
                binding.questionTxt.text = "$num1 + $num2"
                correctAns = num1 + num2
                startTimer()
            }
            if (mode == "Multiplication"){
                binding.edIV.setImageResource(R.drawable.edmultiply)
                binding.questionTxt.text = "$num1 x $num2"
                correctAns = num1 * num2
                startTimer()
            }
            if (mode == "Subtraction"){
                binding.edIV.setImageResource(R.drawable.edminus)
                if (num1 < num2){
                    var temp = num1
                    num1 = num2
                    num2 = temp
                }
                binding.questionTxt.text = "$num1 - $num2"
                correctAns = num1 - num2
                startTimer()
            }
        }
    }
    private fun startTimer(){
        timer = object : CountDownTimer(initialCountDown, countDownInterval) {
            override fun onTick(p0: Long) {
                initialCountDown = p0
                updateText()
            }

            override fun onFinish() {
                timeIsRunning = false
                acceptAns = false
                pauseTimer()
                resetTimer()
                updateText()
                life -= 1
                binding.lifeTxt.text = "$life"
                binding.questionTxt.text = "Times Up!"

            }

        }.start()
    }
    private fun updateText(){
        var tempSecond = (initialCountDown/1000) % 60
        var second:Int = tempSecond.toInt()
        var timeLeft = String.format(Locale.getDefault(), "%02d", second)
        binding.timeTxt.text = "$timeLeft"
    }
    private fun pauseTimer(){
        timer.cancel()
        timeIsRunning = false
    }
    private fun resetTimer(){
        pauseTimer()
        initialCountDown = 20000
        updateText()
    }
}