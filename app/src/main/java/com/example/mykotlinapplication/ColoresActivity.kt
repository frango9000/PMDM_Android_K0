package com.example.mykotlinapplication

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main2.*
import org.jetbrains.anko.browse
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.makeCall
import org.jetbrains.anko.singleTop

class ColoresActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        textView.text = intent.getStringExtra("color")
        btnBack.setOnClickListener { volver(it) }
        buttonCall.setOnClickListener { call(it) }
        buttonGoogle.setOnClickListener { google(it) }

        //cambiar el color del fondo en base al boton presionado
        when(textView.text){
            "Azul" -> layout.setBackgroundColor(Color.BLUE)
            "Verde" -> layout.setBackgroundColor(Color.GREEN)
            "Rojo" -> layout.setBackgroundColor(Color.RED)
            "Amarillo" -> layout.setBackgroundColor(Color.YELLOW)
        }
    }

    //boton navegar a google
    private fun google(it: View?) {
        browse("http://google.es")
    }

    //boton realizar una llamada de emergencia
    private fun call(it: View?) {
        makeCall("112")
    }

    ///volver a la actividad Main
    private fun volver(it: View?) {
        startActivity(intentFor<MainActivity>().singleTop())
    }
}
