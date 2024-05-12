package com.example.tma_lab4

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.tma_lab4.databinding.ActivityLoginBinding

    class Login : AppCompatActivity() {
        private lateinit var binding: ActivityLoginBinding
        private lateinit var databaseHelper: DatabaseHelper

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            binding = ActivityLoginBinding.inflate(layoutInflater)
            setContentView(binding.root)

            databaseHelper = DatabaseHelper(this, null)

            binding.btnLogin.setOnClickListener {
                val loginMail = binding.loginMail.text.toString()
                val loginPassword = binding.loginPassword.text.toString()
                loginDatabase(loginMail, loginPassword)
            }

            binding.signupRedirect.setOnClickListener {
                val intent = Intent(this, SignUp::class.java)
                startActivity(intent)
                finish()
            }

            val sharedPreferences: SharedPreferences =
                getSharedPreferences("tma_lab4", Context.MODE_PRIVATE)
            val isLoggedIn = sharedPreferences.getInt("userId", -1)

            if (isLoggedIn > 0) {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
        }

        @SuppressLint("Range")
        private fun loginDatabase(username: String, password: String) {
            val cursor = databaseHelper.getUser(username, password)
            if (cursor != null) {
                var userId = (cursor.getInt(cursor.getColumnIndex(DatabaseHelper.USER_ID)))

                val sharedPreferences: SharedPreferences =
                    getSharedPreferences("tododemo", Context.MODE_PRIVATE)
                val editor = sharedPreferences.edit()
                editor.putInt("userId", userId)
                editor.apply()

                Toast.makeText(this, "Login successful", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()

                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this, "Login Failed", Toast.LENGTH_SHORT).show()
            }
        }
    }

