package com.labactivity.mathsheeran

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.labactivity.mathsheeran.databinding.ActivityResultBinding

class Result : AppCompatActivity() {
    private lateinit var binding: ActivityResultBinding
    private var scoreList: ArrayList<Long> = ArrayList()
    private var score = 0
    private var mode:String? = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        scoreList = FileHelper().readData(this)

        score = intent.getIntExtra("score", 0)
        mode = intent.getStringExtra("mode")
        binding.finalScoreTxt.text = "$score points"


        if (mode == "Addition"){
            binding.edResIV.setImageResource(R.drawable.edplus)
            checkHighScore(0)
        }
        if (mode == "Subtraction"){
            binding.edResIV.setImageResource(R.drawable.edminus)
            checkHighScore(1)
        }
        if (mode == "Multiplication"){
            binding.edResIV.setImageResource(R.drawable.edmultiply)
            checkHighScore(2)
        }



        binding.retryBtn.setOnClickListener(){
            val intent = Intent(applicationContext, Game::class.java)
            intent.putExtra("mode", mode)
            startActivity(intent)
            this.finish()
        }

        binding.exitBtn.setOnClickListener(){
            val intent = Intent(applicationContext, MainActivity::class.java)
            startActivity(intent)
            this.finish()
        }
    }
    private fun checkHighScore(pos:Int){
        if(score > scoreList[pos]){
            scoreList[pos] = score.toLong()
            binding.highScoreTxt.setTextColor(Color.parseColor("#FD0D0D"))
            binding.highScoreTxt.text = "NEW HIGH SCORE!"
            FileHelper().writeData(scoreList, this)
        } else {
            binding.highScoreTxt.text = "high score: ${scoreList[pos]}"
        }
    }
}