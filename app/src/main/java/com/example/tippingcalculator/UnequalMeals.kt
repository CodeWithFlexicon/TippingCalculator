package com.example.tippingcalculator

import android.app.Person
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ExpandableListAdapter
import android.widget.ExpandableListView
import com.example.tippingcalculator.databinding.FragmentUnequalMealsBinding

class UnequalMeals : Fragment() {

    private var _binding: FragmentUnequalMealsBinding? = null
    private var expandableListView: ExpandableListView? = null
    private var adapter: ExpandableListAdapter? = null
    private var titleList: List<Person>? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentUnequalMealsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    class People {
        private var nameOfPerson = ""

        var items = ArrayList<Item>()
    }

    class Item {
        private var nameOfItem: String ?= null
        private var priceOfItem: Double ?= null
        private var quantityOfItem: Integer ?= null

        fun inputItemName() {

        }

        fun inputItemPrice() {

        }

        fun inputItemQuantity() {

        }
    }

    private fun calculatePeople() {
        val numOfPeople = binding.personsValueEditText

    }

}