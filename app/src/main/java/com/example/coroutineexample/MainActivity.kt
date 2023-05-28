package com.example.coroutineexample

import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.viewbinding.ViewBinding
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.coroutineexample.databinding.ActivityMainBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity(){
    private val viewBinding: ActivityMainBinding by viewBinding()

    lateinit var button : Button
    lateinit var tv1 : TextView
    lateinit var tv2 : TextView
    private var isloaded = false
    lateinit var progressBar : ProgressBar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        with(viewBinding){
            this@MainActivity.button = button
            tv1 = textView
            tv2 = textView2
            this@MainActivity.progressBar = progressBar
        }
        button.setOnClickListener {
            tv1.text = ""
            tv2.text = ""
            progressBar.isVisible = true
            button.isEnabled = false
            loadData()
        }



    }

  fun  loadData(){

   val jobCity =   lifecycleScope.launch {
loadcity()
      }
   val jobTemperature =   lifecycleScope.launch {
          loadTemperature()
      }
      lifecycleScope.launch {
          jobCity.join()
          jobTemperature.join()
         progressBar.isVisible =false
          button.isEnabled = true

      }

  }

    private suspend fun loadcity():Boolean{
        delay(3000)
        tv1.text = "Vienna"
        isloaded = true
        return isloaded
    }
    private suspend fun loadTemperature():Boolean{
        delay(5000)
        tv2.text = "22"
        isloaded = true
        return isloaded
    }


}