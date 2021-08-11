package com.sahu.stackview.fragments

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.sahu.ext.addFragment
import com.sahu.stackview.MainActivity
import com.sahu.stackview.R
import com.sahu.stackview.StackFragment

class ChildFragment2 : StackFragment() {

    companion object {
        fun newInstance() = ChildFragment2()
    }

    override fun attachChildFragment(container: ViewGroup) {
        addFragment(container.id, ChildFragment3.newInstance(), "CHILD2")
    }

    override fun toCollapseView(container: ViewGroup): View {
        container.removeAllViews()
        val view = inflater.inflate(R.layout.collapsed_view, container, false)
        bindCollapsedView(view)
        return view
    }

    private fun bindCollapsedView(view: View){
        view.findViewById<ImageView>(R.id.collapse).setOnClickListener {
            expandAndDetachChildren()
        }
        view.findViewById<TextView>(R.id.collapsedText).text = "Collapsed Text of View 2"
    }

    override fun attachExpandView(container: ViewGroup, isOnCreation: Boolean): View {
        container.removeAllViews()
        val view: View = inflater.inflate(R.layout.expanded_view, container, false)
        bindExpandedView(view)
        return view
    }

    private fun bindExpandedView(view: View){
        if(activity is MainActivity)
            (activity as MainActivity)
                .apply {
                    setButtonSetUp("Add Child View 3") { addChildFragment() }
                    setButtonEnable(true)
                }
        view.findViewById<TextView>(R.id.expandedText).text = "Expanded Text of View 2"
        view.setBackgroundColor(resources.getColor(R.color.view2_bg))
    }
}