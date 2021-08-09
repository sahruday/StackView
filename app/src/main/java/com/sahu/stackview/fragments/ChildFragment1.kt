package com.sahu.stackview.fragments

import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import com.sahu.ext.addFragment
import com.sahu.stackview.R
import com.sahu.stackview.StackFragment

class ChildFragment1 : StackFragment() {

    companion object {
        fun newInstance() = ChildFragment1()
    }

    override fun attachChildFragment(container: ViewGroup) {
        addFragment(container.id, ChildFragment2.newInstance(), "CHILD2")
    }

    override fun toCollapseView(container: ViewGroup): View {
        container.removeAllViews()
        val view = inflater.inflate(R.layout.collapsed_view, container, false)
        view.findViewById<ImageView>(R.id.collapse).setOnClickListener {
            expandAndDetachChildren()
        }
        return view
    }

    override fun attachExpandView(container: ViewGroup): View {
        container.removeAllViews()
        val view = inflater.inflate(R.layout.expanded_view, container, false)
        view.findViewById<Button>(R.id.addChild).setOnClickListener {
            addChildFragment()
        }
        return view
    }
}