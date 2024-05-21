package com.example.evintegradoracinco.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.evintegradoracinco.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var mbinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mbinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mbinding.root)

        CoroutineScope(Dispatchers.Main).launch {
            delay(3000)
            startActivity(Intent(this@MainActivity, ActivityLoginSingUp::class.java))
            finish()
        }

    }
}
