package com.example.funbox.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.funbox.view.activity.MainActivity
import com.example.funbox.viewmodel.PhoneViewModel

open class BaseFragment : Fragment() {

    lateinit var viewModel: PhoneViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(
            requireActivity(),
            (requireActivity() as MainActivity).phoneViewModelFactory
        )[PhoneViewModel::class.java]
    }
}