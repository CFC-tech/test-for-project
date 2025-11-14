package com.example.myapp.auth

import android.net.NetworkCapabilities
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import com.example.myapp.databinding.ActivityCreateAccountBinding
import com.google.firebase.auth.FirebaseAuth

class CakeCreateAccount : AppCompatActivity() {
    private lateinit var binding: ActivityCreateAccountBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateAccountBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btSignup.setOnClickListener {
            val name = binding.txName.editText?.text?.toString()?.trim().orEmpty()
            val email = binding.txEmail.editText?.text?.toString()?.trim().orEmpty()
            val password = binding.txPassword.editText?.text?.toString().orEmpty()
            val confirmPassword = binding.txRetypePassword.editText?.text?.toString().orEmpty()

            binding.txName.editText?.addTextChangedListener { binding.txName.error = null }
            binding.txEmail.editText?.addTextChangedListener { binding.txEmail.error = null }
            binding.txPassword.editText?.addTextChangedListener { binding.txPassword.error = null }
            binding.txRetypePassword.editText?.addTextChangedListener { binding.txRetypePassword.error = null }

            when {
                name.isEmpty() -> {
                    binding.txName.error = "Please enter your name"
                    binding.txName.editText?.requestFocus()
                }
                email.isEmpty() -> {
                    binding.txEmail.error = "Please enter your email"
                    binding.txEmail.editText?.requestFocus()
                }
                !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches() -> {
                    binding.txEmail.error = "Please enter a valid email"
                    binding.txEmail.editText?.requestFocus()
                }
                password.isEmpty() -> {
                    binding.txPassword.error = "Please enter your password"
                    binding.txPassword.editText?.requestFocus()
                }
                password.length < 8 -> {
                    binding.txPassword.error = "Password must be at least 8 characters"
                    binding.txPassword.editText?.requestFocus()
                }
                confirmPassword.isEmpty() -> {
                    binding.txRetypePassword.error = "Please retype your password"
                    binding.txRetypePassword.editText?.requestFocus()
                }
                password != confirmPassword -> {
                    Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show()
                    binding.txPassword.editText?.requestFocus()
                }
                else -> {
                    registerAccount(name, email, password)
                }
            }
        }
    }

    private fun registerAccount(name: String, email: String, password: String) {
        if (checkInternetConnection()) {
            FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(this, "Account created successfully for $name", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(this, "Account creation failed", Toast.LENGTH_SHORT).show()
                    }
                }
        } else {
            Toast.makeText(this, "No internet connection. Please try again later.", Toast.LENGTH_SHORT).show()
        }
    }

    private fun checkInternetConnection(): Boolean {
        val connectivityManager = getSystemService(CONNECTIVITY_SERVICE) as android.net.ConnectivityManager
        val network = connectivityManager.activeNetwork ?: return false
        val networkCapabilities = connectivityManager.getNetworkCapabilities(network) ?: return false
        return networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
    }
}