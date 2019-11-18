package com.example.mykotlinapplication

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main2.*
import org.jetbrains.anko.browse
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.makeCall
import org.jetbrains.anko.singleTop

class Main2Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        textView.text = intent.getStringExtra("color")
        btnBack.setOnClickListener { volver(it) }
        buttonCall.setOnClickListener { call(it) }
        buttonGoogle.setOnClickListener { google(it) }
        when(textView.text){
            "Azul" -> layout.setBackgroundColor(Color.BLUE)
            "Verde" -> layout.setBackgroundColor(Color.GREEN)
        }
    }

    private fun google(it: View?) {
        browse("http://google.es")
    }

    private fun call(it: View?) {
        makeCall("112")
    }

    private fun volver(it: View?) {
        startActivity(intentFor<MainActivity>().singleTop())
    }
}
