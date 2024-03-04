package com.labactivity.mathsheeran

import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.drawable.ColorStateListDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.animation.Animation
import android.view.animation.CycleInterpolator
import android.view.animation.LinearInterpolator
import android.view.animation.RotateAnimation
import com.labactivity.mathsheeran.databinding.ActivitySplashBinding

class Splash : AppCompatActivity() {
    private lateinit var binding:ActivitySplashBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val shakeAnimation = RotateAnimation(-10f, 20f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f)
        shakeAnimation.duration = 500
        shakeAnimation.interpolator = LinearInterpolator()
        shakeAnimation.repeatMode = Animation.REVERSE
        shakeAnimation.repeatCount = 3


        Handler().postDelayed({
            binding.addSymbolIV.setImageResource(R.drawable.addition)
            binding.additionBtn.startAnimation(shakeAnimation)
            Handler().postDelayed({
                binding.subSymbolIV.setImageResource(R.drawable.subtraction)
                binding.subtractionBtn.startAnimation(shakeAnimation)
                Handler().postDelayed({
                    binding.multiplySymbolIV.setImageResource(R.drawable.multiplication)
                    binding.multiplicationBtn.startAnimation(shakeAnimation)
                    Handler().postDelayed({
                        val intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                        this.finish()
                    }, 1000)
                }, 1000)
            },1000)
        }, 500)
    }
}