package com.D121201068.taskmanagement

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.D121201068.taskmanagement.databinding.ActivityIntroBinding

class IntroActivity : AppCompatActivity() {
    private lateinit var binding :ActivityIntroBinding
    lateinit var sharedPreferences: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIntroBinding.inflate(layoutInflater)
        setContentView(binding.root)
        sharedPreferences = getSharedPreferences("SHARED_PREF", Context.MODE_PRIVATE)
        binding.mulai.setOnClickListener {
            val editor:SharedPreferences.Editor = sharedPreferences.edit()
            editor.putBoolean("FinishIntro?",true)
            editor.apply()
            val i = Intent(this, MainActivity::class.java)
            startActivity(i)
            overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out)
            finish()
        }





    }
}