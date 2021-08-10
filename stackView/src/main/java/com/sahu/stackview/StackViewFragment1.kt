package com.sahu.stackview

import androidx.fragment.app.Fragment
import com.sahu.stackview.StackFragment.State.COLLAPSE
import com.sahu.stackview.StackFragment.State.EXPAND

abstract class StackViewFragment1 : Fragment(), StateChanger {

    var mState: StackFragment.State = EXPAND
        set(value) =
            if (value == EXPAND) {
                expandView()
                field = value
            }else{
                collapseView()
                field = value
            }

    fun isExpanded(): Boolean = mState == EXPAND

    fun toggle() {
        mState = if (mState == EXPAND) COLLAPSE else EXPAND
    }

}