package com.sanjaydevtech.cps.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.sanjaydevtech.cps.DeviceUserHelper
import com.sanjaydevtech.cps.DomainData
import com.sanjaydevtech.cps.R
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    @ExperimentalCoroutinesApi
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val textView: TextView = findViewById(R.id.textView)
        val fab: FloatingActionButton = findViewById(R.id.fab)

        val resolverHelper = DeviceUserHelper(this, Handler(Looper.getMainLooper()))


        resolverHelper.titleLiveData.observe(this) {
            textView.text = "Size: ${it.size}"

        }

        fab.setOnClickListener {
            lifecycleScope.launch {
                resolverHelper.insertDomain(DomainData(title = "Hey"))
            }
        }
    }
}