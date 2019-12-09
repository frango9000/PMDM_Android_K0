package com.example.mykotlinapplication

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Color
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.browse
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.singleTop

const val CALC_REQUEST = 1  // The request code

const val REQUEST_IMAGE_CAPTURE = 2

const val REQUEST_TRIVIA_BOOL = 3


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnAzul.setOnClickListener { abrirActivityColores(it) }
        btnVerde.setOnClickListener { abrirActivityColores(it) }
        btnAmarillo.setOnClickListener { abrirActivityColores(it) }
        btnRojo.setOnClickListener { abrirActivityColores(it) }

        btnCalc.setOnClickListener {  abrirActivityCalc(it)}
        btnCam.setOnClickListener { dispatchTakePictureIntent(it) }
        btnBrowse.setOnClickListener { dispatchGoBrowse(it) }

        btnTrivia.setOnClickListener { goTrivia(it) }

    }


    //Accion Boton Navegar A
    private fun dispatchGoBrowse(it: View?) {
        (it as Button).setBackgroundColor(Color.DKGRAY)
        browse(webBrowse.text.toString())
    }

    //Accion Boton Tomar foto y mostrarla en Image View
    private fun dispatchTakePictureIntent(v: View) {
        (v as Button).setBackgroundColor(Color.DKGRAY)
        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->
            takePictureIntent.resolveActivity(packageManager)?.also {
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
            }
        }
    }

    //Accion Iniciar Trivia
    private fun goTrivia(v: View) {
        (v as Button).setBackgroundColor(Color.DKGRAY)

        val intent = Intent(this, TriviaActivity::class.java)
        startActivityForResult(intent, REQUEST_TRIVIA_BOOL)

    }

    //accion ira ColoresActivity
    fun abrirActivityColores(v: View) {
        val btn = v as Button
        btn.setBackgroundColor(Color.DKGRAY)
        startActivity(intentFor<ColoresActivity>("color" to btn.text).singleTop())

    }

    //accion enviar numeros a ser calculados (el resultado sera retornado en el activityForResult)
    fun abrirActivityCalc(v: View){
        (v as Button).setBackgroundColor(Color.DKGRAY)
        var num1 = a1num1.text
        var num2 = a1num2.text

        try {

            var n1 = Integer.parseInt(num1.toString())
            var n2 = Integer.parseInt(num2.toString())

            val intent = Intent(this, CalculadorActivity::class.java)
            intent.putExtra("num1", n1)
            intent.putExtra("num2", n2)

            startActivityForResult(intent, CALC_REQUEST)


        }catch (nfe: NumberFormatException){
            Toast.makeText(this, "Numero Invalido", Toast.LENGTH_SHORT)
        }

    }

    //Recuperamos el dato del resultado y lo mostramos
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        // Check which request we're responding to
        when (requestCode) {
            //Recuperar resultado de calculadora
            CALC_REQUEST -> {
                if (resultCode == Activity.RESULT_OK) {
                    val result = data?.getIntExtra("result", 0)
                    resultado.text = result.toString()
                }
            }
            //recuperar imagen tomada e insertar en imageview
            REQUEST_IMAGE_CAPTURE -> {
                if (resultCode == RESULT_OK) {
                    val imageBitmap = data!!.extras!!.get("data") as Bitmap
                    captura.setImageBitmap(imageBitmap)
                }
            }
            //recuperar resultado de trivia y ajustar el color del boton
            REQUEST_TRIVIA_BOOL -> {
                if (resultCode == RESULT_OK) {
                    val result = data?.getBooleanExtra("bool", false)
                    btnTrivia.setBackgroundColor(if (result!!) Color.GREEN else Color.RED)
                }
            }
        }
    }
}
