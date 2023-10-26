package com.lespsan543.calculadoraimc

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class IMCActivity : AppCompatActivity() {
    lateinit var botonResultado : Button
    lateinit var botonAtras : Button

    //peso
    lateinit var pesoNum: Button
    lateinit var pesoSum: Button
    lateinit var pesoRest: Button
    //edad
    lateinit var edadNum: Button
    lateinit var edadSum: Button
    lateinit var edadRest: Button

    var genero: String = ""
    var resultado = 18.23

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_imcactivity)

        inicializarComponentes()

        inicializarListeners()
    }

    fun inicializarComponentes(){
        botonResultado = findViewById(R.id.botonResultado)
        botonAtras = findViewById(R.id.botonAtrasIMC)

        //peso
        //altura

    }

    fun inicializarListeners(){
        botonResultado.setOnClickListener { pulsarCalcular() }
        botonAtras.setOnClickListener { onBackPressed() }
    }

    fun pulsarCalcular(){
        //Código para obtener el resultado


        val intent = Intent(this,IMCResultadoActivity::class.java)
        intent.putExtra("resultado",resultado)
        startActivity(intent)
    }
}