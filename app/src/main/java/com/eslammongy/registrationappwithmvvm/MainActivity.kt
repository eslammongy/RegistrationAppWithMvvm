package com.eslammongy.registrationappwithmvvm

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.asLiveData
import com.eslammongy.registrationappwithmvvm.data.UserPreferences

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val intent = Intent(this , AuthActivity::class.java)
        val userPreferences = UserPreferences(this)
        userPreferences.authToken.asLiveData().observe(this , Observer {
            Toast.makeText(this, it ?: "Token Is Null", Toast.LENGTH_SHORT).show()
            Handler(Looper.getMainLooper()).postDelayed({ startActivity(intent) } , 2000)

        })
    }
}
