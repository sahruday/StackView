package com.sahu.stackview

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.helper.widget.Flow
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentContainerView
import androidx.fragment.app.findFragment

class StackViewGroup : Flow {

    constructor(context: Context?) : super(context)

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    fun addViewToParent(view: FragmentContainerView, parent: ViewGroup) {
        if (view.id == NO_ID)
            view.id = View.generateViewId()

        parent.addView(view,
            ConstraintLayout.LayoutParams(
                ConstraintLayout.LayoutParams.MATCH_PARENT,
                ConstraintLayout.LayoutParams.MATCH_PARENT
            )
        )

        for (id in referencedIds) {
            val v = parent.findViewById<FragmentContainerView>(id)
            setHeightTo(id, parent, ConstraintSet.WRAP_CONTENT)
            v.getFragment<StackViewFragment1>()?.collapseView()
        }

        referencedIds = referencedIds.toMutableList().also { it.add(view.id) }.toIntArray()
    }

    private fun setHeightTo(view: View, parent: ViewGroup, height: Int) =
        setHeightTo(view.id, parent, height)

    private fun setHeightTo(id: Int, parent: ViewGroup, height: Int) {
        val constraints = ConstraintSet()
        constraints.clone(parent as ConstraintLayout)
        constraints.constrainDefaultHeight(id, height)//ConstraintSet.WRAP_CONTENT
        constraints.constrainDefaultWidth(id, ConstraintSet.MATCH_CONSTRAINT)
        constraints.applyTo(parent)
    }


    fun expandView(view: View, parent: ViewGroup) : Boolean = expandView(view.id, parent)
    fun expandView(id: Int, parent: ViewGroup) : Boolean {
        return if (referencedIds.contains(id)) {

            val position = referencedIds.indexOfLast { it == id }

            for (refId in position until referencedIds.size) {
                if (refId == id) break
                parent.findFragment<Fragment>().activity?.supportFragmentManager?.popBackStack()

            }
            val v = parent.findViewById<FragmentContainerView>(id)
            v.getFragment<StackViewFragment1>()?.expandView()
            true
        }else false
    }

    fun popLastView(parent: ViewGroup) : Boolean =
        if(referencedIds.size > 1)
            expandView(referencedIds[referencedIds.size - 2], parent)
        else false


}