package com.D121201068.taskmanagement

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.get
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.D121201068.taskmanagement.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navControll = findNavController(R.id.fragment)
        binding.bottomnavigationview.menu.getItem(1).isEnabled=false
        binding.bottomnavigationview.setupWithNavController(navControll)

        binding.tambahTugas.setOnClickListener {
            val intent = Intent(this,AddTaskActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val nav = findNavController(R.id.fragment)

        return nav.navigateUp()|| super.onSupportNavigateUp()
    }
}