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

class Child2(private val childListener: OpenChild): StackViewFragment1() {

    companion object{
        fun getInstance(childListener: OpenChild) : Child2 = Child2(childListener)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.child2_layout, container, false)
    }

    override fun expandView() {
        view?.findViewById<ConstraintLayout>(R.id.expand)?.visibility = View.VISIBLE
        view?.findViewById<ConstraintLayout>(R.id.collapse)?.visibility = View.GONE

        if(activity is MainActivity)
            (activity as MainActivity).setTextSetUp("Add Child 3") { childListener.openChild()}
    }

    override fun collapseView() {
        view?.findViewById<ConstraintLayout>(R.id.expand)?.visibility = View.GONE
        view?.findViewById<ConstraintLayout>(R.id.collapse)?.visibility = View.VISIBLE
    }


}