package com.example.tippingcalculator

import android.content.res.Resources
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class ViewPagerAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount() = 2

    override fun createFragment(position: Int): Fragment {
       return when(position) {
           0 -> { EqualMeals() }
           1 -> { UnequalMeals() }
           else -> { throw Resources.NotFoundException("Invalid Position : $position") }
       }
    }
}