package com.example.myapp.auth

import android.content.Intent
import android.net.NetworkCapabilities
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myapp.MainActivity
import com.example.myapp.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth
import androidx.core.content.edit

class CakeLogin : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Binding initialize
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sharedPref = getSharedPreferences("SaveInfo", MODE_PRIVATE)
        val isChecked = sharedPref.getBoolean("isChecked", false)
        if (isChecked) {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }

        binding.btcreate.setOnClickListener {
            startActivity(Intent(this, CakeCreateAccount::class.java))
        }

        binding.btLogin.setOnClickListener {
            val email = binding.etEmail.text.toString().trim()
            val password = binding.etPassword.text.toString().trim()

            if (TextUtils.isEmpty(email)) {
                binding.etEmail.error = "Please enter your email"
                binding.etEmail.requestFocus()
            } else if (TextUtils.isEmpty(password)) {
                binding.etPassword.error = "Please enter your password"
                binding.etPassword.requestFocus()
            } else {
                if (checkInternetConnection()) {
                    loginAccount(email, password)
                } else {
                    val dialog = android.app.AlertDialog.Builder(this)
                    dialog.setTitle("No Internet Connection")
                    dialog.setMessage("Please check your internet connection and try again.")
                    dialog.setPositiveButton("OK") { dialogInterface, _ ->
                        dialogInterface.dismiss()
                    }
                    dialog.show()
                }
            }
        }
    }

    private fun checkInternetConnection(): Boolean {
        val connectivityManager =
            getSystemService(CONNECTIVITY_SERVICE) as android.net.ConnectivityManager
        val network = connectivityManager.activeNetwork ?: return false
        val activeNetwork = connectivityManager.getNetworkCapabilities(network) ?: return false
        return when {
            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
            else -> false
        }
    }

    private fun loginAccount(email: String, password: String) {
        FirebaseAuth.getInstance()
            .signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    if (binding.checkBox2.isChecked) {
                        val sharedPref = getSharedPreferences("SaveInfo", MODE_PRIVATE)
                        sharedPref.edit {
                            putString("email", email)
                            putString("password", password)
                            putBoolean("isChecked", true)
                        }
                    } else {
                        val sharedPref = getSharedPreferences("SaveInfo", MODE_PRIVATE)
                        sharedPref.edit {
                            putBoolean("isChecked", false)
                        }
                    }
                    Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this, MainActivity::class.java))
                    finish()
                } else {
                    val exception = task.exception
                    if (exception is com.google.firebase.auth.FirebaseAuthException) {
                        when (exception.errorCode) {
                            "ERROR_INVALID_EMAIL" -> {
                                binding.etEmail.error = "Invalid email format"
                                binding.etEmail.requestFocus()
                            }
                            "ERROR_USER_NOT_FOUND" -> {
                                binding.etEmail.error = "Email not registered"
                                binding.etEmail.requestFocus()
                            }
                            "ERROR_WRONG_PASSWORD" -> {
                                binding.etPassword.error = "Incorrect password"
                                binding.etPassword.requestFocus()
                            }
                            else -> {
                                Toast.makeText(this, "Login failed", Toast.LENGTH_SHORT).show()
                            }
                        }
                    } else {
                        Toast.makeText(this, "Login failed. Please try again.", Toast.LENGTH_SHORT).show()
                    }
                }
            }
    }
}
