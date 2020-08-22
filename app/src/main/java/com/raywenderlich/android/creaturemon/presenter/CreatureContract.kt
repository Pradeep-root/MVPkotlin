package com.raywenderlich.android.creaturemon.presenter

import com.raywenderlich.android.creaturemon.model.AttributeType

interface CreatureContract{

    interface Presenter{
        fun updateName(name: String)
        fun attributeSelected(attributeType: AttributeType, position : Int)
        fun drawableSelected(drawable : Int)
        fun isDrawableSelected(): Boolean
    }

    interface View
}