package com.example.mysampletask.ui.base

import android.view.View
import androidx.fragment.app.Fragment
import com.example.mysampletask.R

abstract class BaseFragment(layoutRes: Int): Fragment(layoutRes) {
    fun setUpToolBar(title: Int?=null,showNavIcon: Boolean){
        title?.let { it->
        getMainActivity().activityMainBinding.toolbar.title = getString(it)
        }
        if (showNavIcon) getMainActivity().activityMainBinding.toolbar.setNavigationIcon(R.drawable.ic_back_24)
        else getMainActivity().activityMainBinding.toolbar.navigationIcon = null
        getMainActivity().activityMainBinding.toolbar.setNavigationOnClickListener(View.OnClickListener {
            getMainActivity().onBackPressed()
        })
    }

    private fun getMainActivity(): MainActivity{
        return requireActivity() as MainActivity
    }

}