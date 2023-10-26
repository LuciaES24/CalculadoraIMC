package com.lespsan543.calculadoraimc

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class IMCActivity : AppCompatActivity() {
    lateinit var botonResultado : Button
    lateinit var botonAtras : Button

    //peso
    var peso:Int=0
    lateinit var pesoNum: Button
    lateinit var pesoSum: Button
    lateinit var pesoRest: Button
    //edad
    var edad: Int = 0
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
     * @ param g
     */
    fun elegirGenero(g:String){
        genero = g
    }

    fun elegirEdad(op: String){
        if(op.equals("+")){
            edad++
        }else if(op.equals("-")){
            edad++
        }
    }

    private fun elegirPeso(op: String) {
        if(op.equals("+")){
            peso++
        }else if(op.equals("-")){
            peso--
        }
    }

    fun inicializarListeners(){
        botonResultado.setOnClickListener { pulsarCalcular() }
        botonAtras.setOnClickListener { onBackPressed() }
        //peso
        pesoSum.setOnClickListener { elegirPeso("+") }
        pesoRest.setOnClickListener { elegirPeso("-") }
        //edad
        edadSum.setOnClickListener { elegirEdad("+") }
        edadRest.setOnClickListener { elegirEdad("-") }
        //genero
        masculino.setOnClickListener { elegirGenero("m") }
        femenino.setOnClickListener { elegirGenero("f")}


    }

    fun pulsarCalcular(){
        //Código para obtener el resultado


        val intent = Intent(this,IMCResultadoActivity::class.java)
        intent.putExtra("resultado",resultado)
        startActivity(intent)
    }
}