package com.example.tippingcalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tippingcalculator.databinding.ActivityMainBinding
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.customOptionBox.setOnClickListener { binding.customOptionBox.isEnabled = true }
        binding.calculateButton.setOnClickListener{ calculateTip() }
    }

    private fun calculateTip() {
        val stringInTextField = binding.costOfServiceEditText.text.toString()
        val cost = stringInTextField.toDoubleOrNull()
        if (cost == null || cost == 0.0) {
            displayTip(0.0)
            return
        }
        val tipPercentage = when (binding.tipOptions.checkedRadioButtonId) {
            R.id.custom_option -> customTip()
            R.id.option_twenty_percent -> 0.20
            R.id.option_eighteen_percent -> 0.18
            else -> 0.15
        }
        if (tipPercentage == null) {
            displayTip(0.0)
            return
        }
        if (tipPercentage > 1) {
            tipPercentage / 100
            return
        }
        var tip = tipPercentage * cost
        if(binding.roundUpSwitch.isChecked) {
            tip = kotlin.math.ceil(tip)
        }
        if(binding.roundDownSwitch.isChecked) {
            tip = kotlin.math.floor(tip)
        }

        displayTip(tip)
    }

    private fun displayTip(tip : Double) {
        val formattedTip = NumberFormat.getCurrencyInstance().format(tip)
        binding.tipResult.text = getString(R.string.tip_amount, formattedTip)
    }

    private fun customTip(): Double? {
        return binding.customValueEditText.text.toString().toDoubleOrNull()
    }

}
