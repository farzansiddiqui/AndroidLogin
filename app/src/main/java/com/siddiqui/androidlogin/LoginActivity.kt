package com.siddiqui.androidlogin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import com.siddiqui.androidlogin.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.loginBtn.isEnabled = false

        binding.userNameEditText.doOnTextChanged { _, _, _, _ ->
            validateForm()
        }
        binding.passWordEditText.doOnTextChanged { _, _, _, _ ->
            validateForm()
        }
        binding.loginBtn.setOnClickListener {
            performLogin()
        }
    }
    private fun validateForm() {
        val username = binding.userNameEditText.text.toString().trim()
        val password = binding.passWordEditText.text.toString().trim()

        val isUsernameValid = username.length == 10
        val isPasswordValid = password.length == 7 && password.matches(".*\\d.*".toRegex())
                && password.matches(".*[A-Z].*".toRegex())
                && password.matches(".*[!@#\$%^&*()].*".toRegex())

        binding.loginBtn.isEnabled = isUsernameValid && isPasswordValid
    }

    private fun performLogin() {

        val username = binding.userNameEditText.text.toString().trim()
        val password = binding.passWordEditText.text.toString().trim()

        val predefinedUsername = "Fininfocom" //
        val predefinedPassword = "Fin@123" //

        if (username == predefinedUsername && password == predefinedPassword) {
            showToast("Login Successful!")
            startActivity(Intent(this@LoginActivity, MainActivity::class.java))
            finish()
        } else {
            showToast("Invalid username or password.")
        }
    }
    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}