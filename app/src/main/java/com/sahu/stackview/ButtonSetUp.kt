package com.sahu.stackview

interface ButtonSetUp {
    fun setButtonSetUp(text: String, block: () -> Unit)
    fun setButtonEnable(isEnable: Boolean)
}