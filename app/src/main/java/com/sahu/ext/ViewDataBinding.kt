package com.sahu.ext

import android.view.View
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.sahu.stackview.R


fun AppCompatActivity.replaceFragment(
    @IdRes containerId: Int,
    fragment: Fragment,
    tag: String,
    sharedElements: List<Pair<View, String>> = emptyList(),
    addToStack: Boolean = true
) {
    val transaction = supportFragmentManager.beginTransaction()

    if (sharedElements.isNotEmpty()) transaction.setReorderingAllowed(true)

    sharedElements.forEach { pair ->
        transaction.addSharedElement(pair.first, pair.second)
    }

    transaction.replace(containerId, fragment, tag)

    if (addToStack) transaction.addToBackStack(null)

    transaction.commit()
}



fun Fragment.addFragment(
    @IdRes containerId: Int,
    fragment: Fragment,
    tag: String,
    sharedElements: List<Pair<View, String>> = emptyList(),
    addToStack: Boolean = true
) {
    val transaction = childFragmentManager.beginTransaction()

    if (sharedElements.isNotEmpty()) transaction.setReorderingAllowed(true)

    sharedElements.forEach { pair ->
        transaction.addSharedElement(pair.first, pair.second)
    }

    transaction.setCustomAnimations(R.anim.slide_in_up, R.anim.slide_out_down,R.anim.slide_in_down, R.anim.slide_out_up)

    transaction.add(containerId, fragment, tag)

    if (addToStack) transaction.addToBackStack(tag)

    transaction.commit()
}