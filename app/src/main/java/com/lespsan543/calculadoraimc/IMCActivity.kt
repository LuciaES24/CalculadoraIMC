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
    //genero
    var genero: String = ""
    lateinit var masculino: Button
    lateinit var femenino: Button

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
        pesoNum = findViewById(R.id.textoPesoNumero)
        pesoSum = findViewById(R.id.boton1Sumar)
        pesoRest = findViewById(R.id.boton1Restar)
        //altura
        edadNum = findViewById(R.id.textoEdadNumero)
        edadSum = findViewById(R.id.boton2Sumar)
        edadRest = findViewById(R.id.boton2Restar)
        //genero
        masculino = findViewById(R.id.imagenHombre)
        femenino = findViewById(R.id.imagenMujer)
    }

    /**
     *
     */
    fun elegirGenero(genero){

    }

    fun inicializarListeners(){
        botonResultado.setOnClickListener { pulsarCalcular() }
        botonAtras.setOnClickListener { onBackPressed() }
        //peso
        pesoNum.setOnClickListener {  }
        pesoSum.setOnClickListener {  }
        pesoRest.setOnClickListener {  }
        //edad
        edadNum.setOnClickListener {  }
        edadSum.setOnClickListener {  }
        edadRest.setOnClickListener {  }
        //genero
        masculino.setOnClickListener { elegirGenero() }
        femenino.setOnClickListener { elegirGenero()}


    }

    fun pulsarCalcular(){
        //Código para obtener el resultado


        val intent = Intent(this,IMCResultadoActivity::class.java)
        intent.putExtra("resultado",resultado)
        startActivity(intent)
    }
}