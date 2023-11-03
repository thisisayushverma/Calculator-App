package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
//import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.MarginLayoutParams
import android.widget.TextView
import com.example.calculator.databinding.ActivityMainBinding
import com.google.android.material.button.MaterialButton
import net.objecthunter.exp4j.Expression
import net.objecthunter.exp4j.ExpressionBuilder
import java.lang.IllegalArgumentException
//import java.lang.ArithmeticException
import kotlin.ArithmeticException

//import kotlin.math.log

class MainActivity : AppCompatActivity() {

    private lateinit var mainxml:ActivityMainBinding
    private lateinit var exp:Expression
    private var count=0
//    functions
    private fun solve(){
//    if(mainxml.reseltView.text=="") return
    val txt =mainxml.stringView.text.toString()

        exp=ExpressionBuilder(txt).build()

        try {
            val result = exp.evaluate()

            mainxml.reseltView.visibility = View.VISIBLE
            mainxml.reseltView.text = result.toString()
            count=1
        }catch (ex:ArithmeticException){
            Log.e("Exception",ex.toString())
            val str="ERROR"
            mainxml.reseltView.text=str
            count=0
        }catch (ex:IllegalArgumentException){
            Log.e("Exception",ex.toString())
            val str="ERROR"
            mainxml.reseltView.text=str
            count=0
        }
    }
//    private fun sethei(view: View){
//        val txtview=view as TextView
//        val parhei=view.parent as?View
//        if(parhei is ViewGroup){
//            val hei=parhei.height
//            val txthei=(0.15*hei).toInt()
//            txtview.layoutParams.height=txthei
//        }
//    }
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
        mainxml = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainxml.root)
        mainxml.heading.viewTreeObserver.addOnGlobalLayoutListener {
            setmar(mainxml.heading)
        }
//        mainxml.stringView.viewTreeObserver.addOnGlobalLayoutListener {
//            sethei(mainxml.stringView)
//        }
//        mainxml.reseltView.viewTreeObserver.addOnGlobalLayoutListener {
//            sethei(mainxml.reseltView)
//        }
}

    fun getcal(view: View) {
        val btn= view as MaterialButton
        //        val btn = (MaterialButton).view(
        if(btn==mainxml.btnEq){
            if(mainxml.reseltView.text=="") return
            if(mainxml.reseltView.text=="ERROR") {
                mainxml.stringView.text="0"
                mainxml.reseltView.text=""
                return
            }
            mainxml.stringView.text=mainxml.reseltView.text

            solve()
            mainxml.reseltView.text=""
            return
//            dis(vis)
        }
        if(btn==mainxml.btnAc) {

            mainxml.stringView.text = ""
            mainxml.reseltView.text = ""
            return
        }
        if(btn==mainxml.btnC){
            mainxml.stringView.text = ""
            return
        }
        if(btn==mainxml.btnBack){
            if(mainxml.stringView.text=="") return
            val txt= mainxml.stringView.text.toString()
            if(txt[txt.length-1].toString()==".") count--
            val res=txt.substring(0,txt.length-1)
            println(res)
            mainxml.stringView.text=res
            var ch=res
            ch= ch[ch.length-1].toString()
            if(ch>="0" && ch<="9")
                solve()
            return
        }
        var prevstr=mainxml.stringView.text.toString()
        if(btn==mainxml.btnDot && count==0){
            prevstr += btn.text.toString()
            count++
        }
        if(btn!=mainxml.btnBack && btn!=mainxml.btnDot) prevstr += btn.text.toString()


        //        prevstr=prevstr+btn.text
        mainxml.stringView.text = prevstr
        var ch=mainxml.stringView.text.toString()
        ch= ch[ch.length-1].toString()
        if(!(ch=="+" || ch=="-" || ch=="*" || ch=="/" || ch=="%" || ch=="."))
            solve()
    }
}