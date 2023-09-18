package com.siddiqui.androidlogin.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import com.siddiqui.androidlogin.R
import com.siddiqui.androidlogin.databinding.ActivityLoginBinding
import com.siddiqui.androidlogin.model.UserLogin
import com.siddiqui.androidlogin.viewmodel.LoginViewModel

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    lateinit var viewModel: LoginViewModel
    lateinit var  userLogin:UserLogin
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this)[LoginViewModel::class.java]

        userLogin.userName = binding.userNameEditText.text.toString()
        userLogin.userPassword = binding.userNameEditText.text.toString()

        binding.loginBtn.setOnClickListener {
            viewModel.login(userLogin)
        }
    }

}