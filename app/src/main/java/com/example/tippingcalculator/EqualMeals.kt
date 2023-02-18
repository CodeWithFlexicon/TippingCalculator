package com.example.tippingcalculator

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.view.inputmethod.InputMethodManager
import androidx.core.content.ContextCompat.getSystemService
import com.example.tippingcalculator.databinding.FragmentEqualMealsBinding
import java.text.NumberFormat

class EqualMeals : Fragment() {

    private var _binding: FragmentEqualMealsBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentEqualMealsBinding.inflate(inflater, container, false)
        binding.calculateButton.setOnClickListener { calculateTip() }
        binding.costOfServiceEditText.setOnKeyListener { view, keyCode, _ -> handleKeyEvent(view, keyCode) }
        binding.customValueEditText.setOnKeyListener { view, keyCode, _ -> handleKeyEvent(view, keyCode) }
        binding.personsValueEditText.setOnKeyListener { view, keyCode, _ -> handleKeyEvent(view, keyCode) }
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun calculateTip() {
        val stringInTextField = binding.costOfServiceEditText.text.toString()
        val cost = stringInTextField.toDoubleOrNull()
        if (cost == null || cost == 0.0) {
            displayTip(0.0)
            return
        }
        var tipPercentage = when (binding.tipOptions.checkedRadioButtonId) {
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
            tipPercentage /= 100
        }
        var tip = tipPercentage * cost
        if(binding.roundUpSwitch.isChecked) {
            tip = kotlin.math.ceil(tip)
        }
        if(binding.roundDownSwitch.isChecked) {
            tip = kotlin.math.floor(tip)
        }

        val total = cost + tip

        displayTip(tip)
        displayTotal(total)
        displayCostPerPerson(total)
    }

    private fun displayTip(tip : Double) {
        val formattedTip = NumberFormat.getCurrencyInstance().format(tip)
        binding.tipResult.text = getString(R.string.tip_amount, formattedTip)
    }

    private fun displayTotal(total : Double) {
        val totalAmount = NumberFormat.getCurrencyInstance().format(total)
        binding.totalResult.text = getString(R.string.total_amount, totalAmount)
    }

    private fun displayCostPerPerson(total : Double) {
        val numOfPeople = binding.personsValueEditText.text.toString().toInt()
        val costPerPerson = total / numOfPeople
        val formattedCostPerPerson = NumberFormat.getCurrencyInstance().format(costPerPerson)
        binding.totalPerPerson.text = getString(R.string.cost_per_person_amount, formattedCostPerPerson)
    }

    private fun customTip(): Double? {
        return binding.customValueEditText.text.toString().toDoubleOrNull()
    }

    private fun handleKeyEvent(view: View, keyCode: Int): Boolean {
        if (keyCode == KeyEvent.KEYCODE_ENTER) {
            val inputMethodManager =
                requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
            return true
        }
        return false
    }

}