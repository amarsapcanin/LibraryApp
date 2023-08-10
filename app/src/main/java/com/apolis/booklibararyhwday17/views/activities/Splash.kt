package com.apolis.booklibararyhwday17.views.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.apolis.booklibararyhwday17.R
import com.apolis.booklibararyhwday17.databinding.ActivitySplashBinding
import com.apolis.booklibararyhwday17.utils.Utils

class Splash : AppCompatActivity() {
    private lateinit var binding: ActivitySplashBinding
    private lateinit var utils: Utils
    private val SPLASH_DELAY: Long = 1300

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        utils = Utils(applicationContext)


        val isLogin = utils.getBoolean("isLogin", false)


        Handler().postDelayed({

            if(isLogin){
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }else{
                val intent = Intent(this, Login::class.java)
                startActivity(intent)
            }

            finish()
        }, SPLASH_DELAY)

    }
}