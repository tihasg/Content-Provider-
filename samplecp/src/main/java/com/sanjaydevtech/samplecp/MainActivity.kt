package com.sanjaydevtech.samplecp

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    @ExperimentalCoroutinesApi
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val textView: TextView = findViewById(R.id.textView)
        val fab: FloatingActionButton = findViewById(R.id.fab)

       lifecycleScope.launchWhenCreated {
           getDeviceProvider()
       }
    }

    private suspend fun getDeviceProvider(): List<String> {
        return withContext(Dispatchers.IO) {
            val titles = mutableListOf<String>()

            val cursor = contentResolver!!.query(
                DeviceContract.DomainEntry.DOMAIN_URI,
                null,
                null,
                null,
                null
            )
            cursor?.let {
                val titleIndex = it.getColumnIndex(DeviceContract.DomainEntry.TITLE)
                while (it.moveToNext()) {
                    titles.add(it.getString(titleIndex))
                }
            }
            cursor?.close()
            return@withContext titles
        }

    }
}