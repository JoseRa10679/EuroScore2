package com.benasque.quista.jr.euroscore2.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.benasque.quista.jr.euroscore2.databinding.ActivityAcercaBinding

class AcercaActivity : AppCompatActivity() {

    private val binding by lazy{
        ActivityAcercaBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.btnAceptarAbout.setOnClickListener { finish() }

    }
}