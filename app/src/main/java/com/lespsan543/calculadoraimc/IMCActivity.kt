package com.lespsan543.calculadoraimc

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class IMCActivity : AppCompatActivity() {

    lateinit var botonResultado : Button
    lateinit var botonAtras : Button
    //peso
    var peso:Int=0
    lateinit var pesoNum: TextView
    lateinit var pesoSum: Button
    lateinit var pesoRest: Button
    //edad
    var edad: Int = 0
    lateinit var edadNum: TextView
    lateinit var edadSum: Button
    lateinit var edadRest: Button
    //genero
    var genero: String = ""
    lateinit var masculino: Button
    lateinit var femenino: Button

    var resultado = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_imcactivity)

        inicializarComponentes()

        inicializarListeners()
    }


    /**
     * inicializa todos los componentes
     */
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
     * @ param g se le pasa el tipo de genero
     * se guarda en la variable global genero la variable pasada por parametros
     */
    fun elegirGenero(g:String){
        genero = g
    }

    /**
     * @param op se le pasa el tipo de operacion + o -
     * Si es + se suma 1 a la edad
     * Si es - se resta 1 a la edad
     */
    fun elegirEdad(op: String){
        if(op == "+"){
            edad++
        }else if(op == "-"){
            edad--
        }
        edadNum.text = edad.toString()
    }

    /**
     * @param op se le pasa el tipo de operacion + o -
     * Si es + se suma 1 al peso
     * Si es - se resta 1 al peso
     */
    private fun elegirPeso(op: String) {
        if(op == "+"){
            peso++
        }else if(op == "-"){
            peso--
        }
        pesoNum.text = peso.toString()
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