package com.sahu.stackview.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.sahu.stackview.R

class ChildFragment2 : Fragment() {

    companion object {
        fun newInstance() = ChildFragment2()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.expanded_view, container, false)
    }
}