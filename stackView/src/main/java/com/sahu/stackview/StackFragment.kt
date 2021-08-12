package com.sahu.stackview

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.annotation.CallSuper
import androidx.fragment.app.Fragment

abstract class StackFragment :
    Fragment(R.layout.stack_fragment) {

    var state: State = State.EXPAND
    lateinit var inflater: LayoutInflater

    companion object {
        const val STATE = "STATE"
    }

    enum class State {
        EXPAND,
        COLLAPSE
    }

    final override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        this.inflater = inflater

        if (savedInstanceState != null)
            state = State.values()[savedInstanceState.getInt(STATE)]

        val view = inflater.inflate(R.layout.stack_fragment, container, false)

        addBackPressListener()
        return view
    }

    open fun addBackPressListener() {
        onBackPressed()
    }

    //Handle individually for every stack fragment.
    private fun onBackPressed() {
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            if(childFragmentManager.backStackEntryCount > 0) {
                val childFrag = childFragmentManager.fragments[0]
                if (childFrag.childFragmentManager.backStackEntryCount > 0)
                    childFrag.childFragmentManager.popBackStack()
                else
                    expandAndDetachChildren()
            }
            else {
                val parentFragment = (this@StackFragment).parentFragment
                if( parentFragment is StackFragment)
                    parentFragment.expandAndDetachChildren()
                else
                    parentFragmentManager.popBackStack()
            }
        }
    }

    //Handle from top most fragment.
    //Need to add only for top fragment.
//    private fun handleBackPressListener() {
//        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
//            var childFM = childFragmentManager
//
//            //when children were none pop top fragment or parents stack.
//            if (childFM.backStackEntryCount == 0) {
//                parentFragmentManager.popBackStack()
//                return@addCallback
//            }
//
//            var fragment: Fragment = this@StackFragment
//            //while current has child and grand child, move to child.
//            while (childFM.backStackEntryCount != 0 && childFM.fragments[0].childFragmentManager.backStackEntryCount != 0) {
//                fragment = childFM.fragments[0]
//                childFM = fragment.childFragmentManager
//            }
//
//            if (childFM.backStackEntryCount != 0)
//                if (fragment is StackFragment)
//                    fragment.expandAndDetachChildren()
//                else
//                    fragment.activity?.onBackPressed()
//        }
//
//    }

    @CallSuper
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        when (state) {
            State.EXPAND -> expandView(true)
            State.COLLAPSE -> addChildFragment(savedInstanceState == null)
        }
    }

    /**
     * Use this to expand the current fragment and remove all it children.
     */
    fun expandAndDetachChildren() {
        if (state == State.COLLAPSE) {
            if (childFragmentManager.backStackEntryCount != 0) {
                childFragmentManager.popBackStack()
            }
//            expandView()
            Handler(Looper.myLooper()!!).postDelayed({ expandView() }, 500)
        }
    }

    /**
     * attach child Fragment to [container].
     */
    abstract fun attachChildFragment(container: ViewGroup)

    /**
     * Handle view to collapse state
     */
    abstract fun toCollapseView(container: ViewGroup): View

    /**
     * call this to attach or add child fragment which calls
     * [attachChildFragment] to attach to the container
     * and [toCollapseView]
     *
     * @see [attachChildFragment]
     * @see [toCollapseView]
     */
    open fun addChildFragment(addChild: Boolean = true) {
        if (addChild)
            addContainer()

        val placeHolder: ViewGroup = requireView().findViewById(R.id.placeHolder)
        placeHolder.addView(toCollapseView(placeHolder))
        placeHolder.layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )

        state = State.COLLAPSE
    }

    /**
     * attach Expanded View to the [container]
     */
    abstract fun attachExpandView(container: ViewGroup, isOnCreation: Boolean): View

    /**
     * call this to expand the current view and remove its children.
     * @see [attachExpandView]
     *
     * @param isOnCreation is used not to animate from collapse state.
     */
    open fun expandView(isOnCreation: Boolean = false) {
        val placeholder: ViewGroup = requireView().findViewById(R.id.placeHolder)
        placeholder.addView(attachExpandView(placeholder, isOnCreation))
        placeholder.layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )

        state = State.EXPAND
    }

    protected fun addContainer() {
        val container: ViewGroup = requireView().findViewById(R.id.container)

        container.visibility = View.VISIBLE
        attachChildFragment(container)
    }


    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(STATE, state.ordinal)
    }


}