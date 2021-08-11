package com.sahu.stackview.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.sahu.stackview.MainActivity
import com.sahu.stackview.R

class ChildFragment3 : Fragment() {

    companion object {
        fun newInstance() = ChildFragment3()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.expanded_view, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<Button>(R.id.addChild).visibility = View.GONE
        view.findViewById<TextView>(R.id.expandedText).text = "Expanded Text of View 3"
        view.setBackgroundColor(resources.getColor(R.color.view3_bg))
        if (activity is MainActivity) {
            (activity as MainActivity)
                .apply {
                    setButtonSetUp("This is Child View 3 Button ( No Click )") { }
                    setButtonEnable(false)
                }

        }

    }
}