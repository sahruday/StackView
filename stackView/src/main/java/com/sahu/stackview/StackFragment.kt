package com.sahu.stackview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment

abstract class StackFragment(private val isTopFragment: Boolean = false) : Fragment(R.layout.stack_fragment) {

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

        handleBackPressListener()
        return view
    }

    protected fun handleBackPressListener(){
        if(isTopFragment) //Removes inner nested fragment on back press
            requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    var childFM = childFragmentManager

                    //when children were none pop top fragment or parents stack.
                    if(childFM.backStackEntryCount == 0) {
                        parentFragmentManager.popBackStack()
                        return
                    }

                    var fragment : Fragment = this@StackFragment
                    while (true){
                        //current has child and grand child, moving to child.
                        if(childFM.backStackEntryCount > 0 && childFM.fragments[0].childFragmentManager.backStackEntryCount > 0) {
                            fragment = childFM.fragments[0]
                            childFM = fragment.childFragmentManager
                        }else break
                    }

                    if (childFM.backStackEntryCount > 0)
                        if(fragment is StackFragment)
                            fragment.expandAndDetachChildren()
                        else
                            fragment.activity?.onBackPressed()
                }
            })


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        when (state) {
            State.EXPAND -> expandView()
            State.COLLAPSE -> {
                addChildFragment(savedInstanceState == null)
            }
        }
    }

    fun expandAndDetachChildren(){
        if(state == State.COLLAPSE) {
            expandView()
            if (childFragmentManager.backStackEntryCount != 0)
                childFragmentManager.popBackStackImmediate()
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
        if(addChild)
            addContainer()

        val placeHolder: ViewGroup = requireView().findViewById(R.id.placeHolder)
        placeHolder.addView(toCollapseView(placeHolder))
        placeHolder.layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)

        state = State.COLLAPSE
    }

    /**
     * attach Expanded View to the [container]
     */
    abstract fun attachExpandView(container: ViewGroup): View

    /**
     * call this to expand the current view and remove its children.
     * @see [attachExpandView]
     */
    open fun expandView() {
        val placeholder: ViewGroup = requireView().findViewById(R.id.placeHolder)
        placeholder.addView(attachExpandView(placeholder))
        placeholder.layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)

        removeContainer()

        state = State.EXPAND
    }

    protected fun addContainer() {
        val container: ViewGroup = requireView().findViewById(R.id.container)

        container.visibility = View.VISIBLE
        attachChildFragment(container)
    }

    protected fun removeContainer(){
        val container: ViewGroup = requireView().findViewById(R.id.container)
        container.removeAllViews()

        container.visibility = View.GONE
    }


    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(STATE, state.ordinal)
    }


}