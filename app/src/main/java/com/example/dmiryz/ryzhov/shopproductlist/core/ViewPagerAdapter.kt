package com.example.dmiryz.ryzhov.shopproductlist.core

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class ViewPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    var listFrament:MutableList<Fragment> = ArrayList()
    val lstTitles:MutableList<String> = ArrayList()

    override fun getItem(position: Int): Fragment  = listFrament[position]

    override fun getCount(): Int  = lstTitles.size

    override fun getPageTitle(position: Int): CharSequence?  = lstTitles[position]

    fun addFragment(fragment: Fragment,title: String){
        listFrament.add(fragment)
        lstTitles.add(title)
    }

}