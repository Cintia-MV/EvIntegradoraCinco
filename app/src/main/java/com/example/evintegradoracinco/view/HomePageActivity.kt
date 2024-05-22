package com.example.evintegradoracinco.view

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.evintegradoracinco.R
import com.example.evintegradoracinco.databinding.ActivityHomePageBinding

class HomePageActivity : AppCompatActivity() {
    //Instancia de viewBinding para ActivityHomePage
    private lateinit var hMPBinding: ActivityHomePageBinding

    //Inflar la vista
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        hMPBinding = ActivityHomePageBinding.inflate(layoutInflater)
        setContentView(hMPBinding.root)

    }
}