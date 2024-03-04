package com.labactivity.mathsheeran

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.labactivity.mathsheeran.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.additionBtn.setOnClickListener(){
            val intent = Intent(this, Game::class.java)
            intent.putExtra("mode","Addition")
            startActivity(intent)
            this.finish()
        }

        binding.subtractionBtn.setOnClickListener(){
            val intent = Intent(this, Game::class.java)
            intent.putExtra("mode","Subtraction")
            startActivity(intent)
            this.finish()
        }

        binding.multiplicationBtn.setOnClickListener(){
            val intent = Intent(this, Game::class.java)
            intent.putExtra("mode","Multiplication")
            startActivity(intent)
            this.finish()
        }
    }
}