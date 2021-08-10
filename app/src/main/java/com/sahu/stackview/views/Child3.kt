package com.sahu.stackview.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import com.sahu.stackview.MainActivity
import com.sahu.stackview.OpenChild
import com.sahu.stackview.R
import com.sahu.stackview.StackViewFragment1

class Child3: StackViewFragment1() {

    companion object{
        fun getInstance() : Child3 = Child3()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.child3_layout, container, false)
    }

    override fun expandView() {
        view?.findViewById<ConstraintLayout>(R.id.expand)?.visibility = View.VISIBLE
        view?.findViewById<ConstraintLayout>(R.id.collapse)?.visibility = View.GONE

        if(activity is MainActivity)
            (activity as MainActivity).setTextSetUp("No More children ( No Click )") { }
    }

    override fun collapseView() {
        view?.findViewById<ConstraintLayout>(R.id.expand)?.visibility = View.GONE
        view?.findViewById<ConstraintLayout>(R.id.collapse)?.visibility = View.VISIBLE
    }

}