package com.sahu.stackview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.FrameLayout
import androidx.activity.OnBackPressedCallback
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentContainerView
import com.sahu.ext.addFragment
import com.sahu.stackview.views.Child1
import com.sahu.stackview.views.Child2
import com.sahu.stackview.views.Child3

class MainFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        addBackPressListener()
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    private fun addBackPressListener() {
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    val grp = requireView().findViewById<StackViewGroup>(R.id.grp)
                    if(grp.popLastView(requireView().findViewById(R.id.parent)).not()) {
                        if (grp.referencedIds.size < 2)
                            activity?.supportFragmentManager?.popBackStack()
                    }
                }
            })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        addChild1()
    }

    private fun addChild1() {
        val parent = requireView().findViewById<ConstraintLayout>(R.id.parent)
        val grp = requireView().findViewById<StackViewGroup>(R.id.grp)

        val child1Container = FragmentContainerView(requireContext())
        grp.addViewToParent(child1Container, parent)

        addFragment(child1Container.id, Child1.getInstance(object : OpenChild {
            override fun openChild() {
                addChild2()
            }
        }) , "CHILD_1")

    }

    private fun addChild2() {
        val parent = requireView().findViewById<ConstraintLayout>(R.id.parent)
        val grp = requireView().findViewById<StackViewGroup>(R.id.grp)

        val child2Container = FragmentContainerView(requireContext())
        grp.addViewToParent(child2Container, parent)

        addFragment(child2Container.id, Child2.getInstance(object : OpenChild {
            override fun openChild() {
                addChild3()
            }
        }) , "CHILD_2")
    }

    private fun addChild3() {
        val parent = requireView().findViewById<ConstraintLayout>(R.id.parent)
        val grp = requireView().findViewById<StackViewGroup>(R.id.grp)

        val child3Container = FragmentContainerView(requireContext())
        grp.addViewToParent(child3Container, parent)

        addFragment(child3Container.id, Child3.getInstance() , "CHILD_3")
    }




}
