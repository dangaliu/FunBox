package com.example.funbox.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.core.view.MenuProvider
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.funbox.R
import com.example.funbox.databinding.ActivityMainBinding
import com.example.funbox.model.repository.PhoneRepository
import com.example.funbox.model.storage.CsvPhoneStorage
import com.example.funbox.viewmodel.PhoneViewModel
import com.example.funbox.viewmodel.PhoneViewModelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val phoneStorage = CsvPhoneStorage()
    val phoneRepository = PhoneRepository(phoneStorage)
    lateinit var phoneViewModelFactory: PhoneViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val navController = findNavController(R.id.navHostFragment)
        binding.bottomNavigationView.setupWithNavController(navController)
    }

    private fun init() {
        phoneViewModelFactory = PhoneViewModelFactory(phoneRepository)
    }
}