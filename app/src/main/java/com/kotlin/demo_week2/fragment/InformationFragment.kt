package com.kotlin.demo_week2.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.kotlin.demo_week2.R
import com.kotlin.demo_week2.unit.unitAp
import kotlinx.android.synthetic.main.fragment_information.*

class InformationFragment : Fragment() {
    private val TAG: String = "InformationFragment"


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_information, container, false)
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume")
        initView()
    }

    private fun initView() {
        tv_name.text = "Name: " + unitAp.currentUser?.userName
        tv_email.text = "Email: " + unitAp.currentUser?.email
        tv_address.text = "Address: " + unitAp.currentUser?.address
        tv_gender.text = "Gender: " + unitAp.currentUser?.gender
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.d(TAG, "onDestroyView")
    }
}
