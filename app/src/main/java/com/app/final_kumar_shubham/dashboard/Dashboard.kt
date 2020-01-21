package com.app.final_kumar_shubham.dashboard

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.app.final_kumar_shubham.R
import com.app.final_kumar_shubham.authentication.AuthWelcome

class Dashboard : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val mWelcomeFragment=AuthWelcome()
        supportFragmentManager.beginTransaction().replace(R.id.activity_dashboard,mWelcomeFragment).commit()
    }
}
