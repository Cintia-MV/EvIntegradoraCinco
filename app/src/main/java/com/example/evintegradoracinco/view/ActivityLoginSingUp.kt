package com.example.evintegradoracinco.view

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.evintegradoracinco.R
import com.example.evintegradoracinco.databinding.ActivityLoginSingUpBinding
import com.example.evintegradoracinco.databinding.ActivityMainBinding

class ActivityLoginSingUp : AppCompatActivity() {
    //Instancia de viewBinding para ActivityLoginSingUp
    private lateinit var aclBinding: ActivityLoginSingUpBinding

    //Inflar la vista
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        aclBinding = ActivityLoginSingUpBinding.inflate(layoutInflater)
        setContentView(aclBinding.root)

    }

}