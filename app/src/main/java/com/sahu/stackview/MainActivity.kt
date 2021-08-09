package com.sahu.stackview

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.sahu.ext.replaceFragment
import com.sahu.stackview.fragments.MainFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if(savedInstanceState == null)
            replaceFragment(R.id.container, MainFragment.newInstance(), "MAIN_FRAGMENT")
    }

}