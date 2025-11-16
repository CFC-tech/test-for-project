package com.example.myapp.auth

import android.content.Intent
import android.net.NetworkCapabilities
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import com.example.myapp.databinding.ActivityForgetPasswordBinding
import com.google.firebase.auth.FirebaseAuth

class ForgetPassword : AppCompatActivity() {

    private lateinit var binding: ActivityForgetPasswordBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityForgetPasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Clear error when user types
        binding.fgEmail.editText?.addTextChangedListener {
            binding.fgEmail.error = null
        }

        // Reset Password Button Click
        binding.ResetPassword.setOnClickListener {
            val email = binding.fgEmail.editText?.text.toString().trim()

            if (email.isEmpty()) {
                binding.fgEmail.error = "Please enter your email"
                binding.fgEmail.editText?.requestFocus()
                return@setOnClickListener
            }

            if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                binding.fgEmail.error = "Please enter a valid email"
                binding.fgEmail.editText?.requestFocus()
                return@setOnClickListener
            }

            if (!checkInternetConnection()) {
                Toast.makeText(this, "No Internet Connection", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            sendPasswordResetLink(email)
        }
    }

    private fun sendPasswordResetLink(email: String) {
        FirebaseAuth.getInstance().sendPasswordResetEmail(email)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this, "Password Reset Link Sent to your Email", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this, CakeLogin::class.java))
                    finish()
                } else {
                    Toast.makeText(this, "Error: ${task.exception?.message}", Toast.LENGTH_LONG).show()
                }
            }
    }

    private fun checkInternetConnection(): Boolean {
        val connectivityManager = getSystemService(CONNECTIVITY_SERVICE) as android.net.ConnectivityManager
        val network = connectivityManager.activeNetwork ?: return false
        val activeNetwork = connectivityManager.getNetworkCapabilities(network) ?: return false

        return activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ||
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) ||
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)
    }
}
