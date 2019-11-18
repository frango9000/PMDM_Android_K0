package com.example.mykotlinapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnAzul.setOnClickListener{abrirActivity2(it)}
        btnVerde.setOnClickListener{abrirActivity2(it)}
    }


    fun abrirActivity2(v: View){
        val btn = v as Button
        val intent = Intent(this, Main2Activity::class.java)
        intent.putExtra("color", btn.text)
        startActivity(intent)

    }
}
