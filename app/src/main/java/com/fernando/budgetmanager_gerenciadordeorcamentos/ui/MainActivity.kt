package com.fernando.budgetmanager_gerenciadordeorcamentos.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import com.fernando.budgetmanager_gerenciadordeorcamentos.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onStart() {
        super.onStart()
        findNavController(binding.fragmentContainerView.id).addOnDestinationChangedListener { _, destination, _ ->
            binding.toolbarTitle.text = destination.label.toString()
        }
    }
}