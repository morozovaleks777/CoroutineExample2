package com.example.coroutineexample

import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.viewbinding.ViewBinding
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.coroutineexample.databinding.ActivityMainBinding
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity(){
    private val viewBinding: ActivityMainBinding by viewBinding()
val city = "Vienna"
    val temp = "22"
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

   val deferredCity =   lifecycleScope.async {
loadcity()
       city
      }
   val deferredTemperature =   lifecycleScope.async {
          loadTemperature()
       temp
      }
      lifecycleScope.launch{
      val city =   deferredCity.await()
       val temp =    deferredTemperature.await()
         progressBar.isVisible =false
          button.isEnabled = true
Toast.makeText(this@MainActivity,
    "city: $city temperature: $temp",
    Toast.LENGTH_SHORT).show()
      }

  }

    private suspend fun loadcity():Boolean{
        delay(3000)
        val city = "city: $city"
        tv1.text = city
        isloaded = true
        return isloaded
    }
    private suspend fun loadTemperature():Boolean{
        delay(5000)
        val temperature = "temperature: $temp \u00B0 c"
        tv2.text = temperature
        isloaded = true
        return isloaded
    }


}