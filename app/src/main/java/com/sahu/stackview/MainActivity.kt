package com.sahu.stackview

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.sahu.ext.replaceFragment
import com.sahu.stackview.fragments.ChildFragment1

class MainActivity : AppCompatActivity(), TextSetUp {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if(savedInstanceState == null)
            replaceFragment(R.id.container, ChildFragment1.newInstance(), "MAIN_FRAGMENT")
    }

    override fun setTextSetUp(text: String, block: () -> Unit) {
        val button: Button? = findViewById(R.id.children)
        button?.text = text
        button?.setOnClickListener { block.invoke() }
    }
}