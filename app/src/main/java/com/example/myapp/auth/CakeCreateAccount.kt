package com.example.myapp.auth

import android.net.NetworkCapabilities
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myapp.R
import com.example.myapp.databinding.ActivityCreateAccountBinding
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth

class CakeCreateAccount : AppCompatActivity() {
    private lateinit var binding: ActivityCreateAccountBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateAccountBinding.inflate(layoutInflater)

        binding.btSignup.setOnClickListener {
            var name = binding.txName.editText?.text.toString()
            var email = binding.txEmail.editText?.text.toString()
            var password = binding.txPassword.editText?.text.toString()
            var confirmPassword = binding.txRetypePassword.editText?.text.toString()

            if(TextUtils.isEmpty(name)){
                binding.txName.error = "Please enter your name"
                binding.txName.requestFocus()
            } else if (TextUtils.isEmpty(email)){
                binding.txEmail.error = "Please enter your email"
                binding.txEmail.requestFocus()
            } else if (TextUtils.isEmpty(password)){
                binding.txPassword.error = "Please enter your password"
                binding.txPassword.requestFocus()
            } else if (TextUtils.isEmpty(confirmPassword)){
                binding.txRetypePassword.error = "Please retype your password"
                binding.txRetypePassword.requestFocus()
            } else if (!password.equals(confirmPassword)){
                Toast.makeText(this, "Password do not match", Toast.LENGTH_SHORT).show()
            } else {
                registerAccount(name,email,password)
            }
             

        }

        setContentView(binding.root)
    }
    private fun registerAccount(name: String, email: String, password: String) {
        if(checkInternetConnection()){
            FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(this, "Account created successfully for $name", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(this, "Account creation failed", Toast.LENGTH_SHORT).show()
                    }
                }
        }else{
            Toast.makeText(this, "No internet connection. Please try again later.", Toast.LENGTH_SHORT).show()
        }
    }
    private fun checkInternetConnection(): Boolean {
        val connectivityManager = getSystemService(CONNECTIVITY_SERVICE) as android.net.ConnectivityManager
        val network = connectivityManager.activeNetwork?: return false
        val networkCapabilities = connectivityManager.getNetworkCapabilities(network)?: return false
        return networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
    }

}
