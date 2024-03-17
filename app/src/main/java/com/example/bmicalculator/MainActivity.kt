package com.example.bmicalculator

import android.os.Bundle
import android.widget.NumberPicker
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.bmicalculator.databinding.ActivityMainBinding

class MainActivity : ComponentActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { binding = ActivityMainBinding.inflate(layoutInflater)
          setContentView(binding.root)

            binding.weightPicker.minValue = 30
            binding.weightPicker.maxValue = 150

            binding.heightPicker.minValue = 100
            binding.heightPicker.maxValue = 250

            binding.weightPicker.setOnValueChangedListener{ _, _, _->
                CalculateBMI()
            }

            binding.heightPicker.setOnValueChangedListener{_, _, _->
                CalculateBMI()
            }

        }


    }
    private fun CalculateBMI(){
      var height = binding.heightPicker.value
        var  doubleHeight = height.toDouble()/100

        var weight = binding.weightPicker.value
        var bmi = weight.toDouble()/ (doubleHeight*doubleHeight)
        binding.resultsTV.text = String.format("Your BMI is: %.2f", bmi)
        binding.healthyTV.text = String.format("Considered: %s", healthyMessage(bmi))
    }

    private fun healthyMessage(bmi: Double): String{
        if (bmi<18)
            return "Underweight"


        if (bmi<25)
            return "Healthy"

        if (bmi<30)
            return "Overweight"
        return "Obese"
    }
}