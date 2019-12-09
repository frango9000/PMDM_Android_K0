package com.example.mykotlinapplication

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_trivia.*
import java.util.*

class TriviaActivity : AppCompatActivity() {
    val list = mutableListOf<Cientifico>()
    var n = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_trivia)

        //lista de datos correctos
        list.add(Cientifico("Marie Curie", 1867))
        list.add(Cientifico("Albert Einstein", 1879))
        list.add(Cientifico("Wernher von Braun", 1912))
        list.add(Cientifico("Galileo Galilei", 1564))
        list.add(Cientifico("Stephen Hawking", 1942))

        n = Random().nextInt(list.size - 1)

        textViewNombre.text = list[n].nombre

        btnGoTrivia.setOnClickListener { responder(it) }
    }

    //verificar respuesta y retornar resultado true/false
    private fun responder(it: View?) {
        val intent = Intent()
        var resp: Boolean = false
        try {
            var anho = Integer.parseInt(textRespuesta.text.toString())
            resp = anho == list[n].anho
        } catch (e: Exception) {
        } finally {

            intent.putExtra("bool", resp)
            setResult(Activity.RESULT_OK, intent)
            finish()
        }
    }


}

//clase de datos a solicitar
data class Cientifico(
    val nombre: String,
    val anho: Int
)