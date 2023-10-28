package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.MarginLayoutParams
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private lateinit var textView:TextView
    private fun setmar(textView:TextView){
        val laymar=textView.layoutParams


        val parcontent=textView.parent as?View
        if(parcontent is ViewGroup) {
            val parheight = parcontent.height
            val topmarin=(0.025*parheight).toInt()
            if (laymar is MarginLayoutParams) {
                laymar.topMargin=topmarin
                textView.layoutParams=laymar
                textView.requestLayout()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        textView=findViewById(R.id.heading)
        textView.viewTreeObserver.addOnGlobalLayoutListener {
            setmar(textView)
        }
    }
}