package com.sahu.stackview.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import com.sahu.stackview.MainActivity
import com.sahu.stackview.OpenChild
import com.sahu.stackview.R
import com.sahu.stackview.StackViewFragment1

class Child1(private val childListener: OpenChild): StackViewFragment1() {

    companion object{
        fun getInstance(childListener: OpenChild) : Child1 = Child1(childListener)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.child1_layout, container, false)
    }

    override fun expandView() {
        view?.findViewById<ConstraintLayout>(R.id.expand)?.visibility = VISIBLE
        view?.findViewById<ConstraintLayout>(R.id.collapse)?.visibility = GONE

        if(activity is MainActivity)
            (activity as MainActivity).setTextSetUp("Add Child 2") { childListener.openChild() }
    }

    override fun collapseView() {
        view?.findViewById<ConstraintLayout>(R.id.expand)?.visibility = GONE
        view?.findViewById<ConstraintLayout>(R.id.collapse)?.visibility = VISIBLE
    }

}