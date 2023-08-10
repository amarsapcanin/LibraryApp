package com.apolis.booklibararyhwday17.views.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.apolis.booklibararyhwday17.R
import com.apolis.booklibararyhwday17.databinding.ActivityLoginBinding
import com.apolis.booklibararyhwday17.utils.Utils

class Login : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var utils: Utils
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        utils = Utils(applicationContext)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLogin.setOnClickListener {

            utils.saveString("user", binding.etUsername.text.toString())
            utils.saveString("pass", binding.etPassword.text.toString())

            utils.saveBoolean("isLogin", true)

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }


    }
}