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
import com.eslammongy.registrationappwithmvvm.ui.home.HomeActivity
import com.eslammongy.registrationappwithmvvm.ui.startNewActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val userPreferences = UserPreferences(this)
        userPreferences.authToken.asLiveData().observe(this , Observer {
            val activityShouldBeStart = if (it == null) AuthActivity::class.java else HomeActivity::class.java
            Handler(Looper.getMainLooper()).postDelayed({ startNewActivity(activityShouldBeStart) } , 2000)

        })
    }
}
