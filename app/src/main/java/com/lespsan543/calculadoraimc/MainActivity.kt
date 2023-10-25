package com.lespsan543.calculadoraimc

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    /**
     * Creamos una variable para cada botón
     */

    lateinit var botonCalcJoseLuis : Button
    lateinit var botonCalcJorge : Button
    lateinit var botonCalcLucia : Button
    lateinit var botonIMC : Button
    lateinit var botonSalir : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        inicializarComponentes()

        inicializarListeners()
    }

    fun inicializarComponentes(){
        botonCalcJoseLuis = findViewById(R.id.)
        botonCalcJorge = findViewById(R.id.)
        botonCalcLucia = findViewById(R.id.)
        botonIMC = findViewById(R.id.)
        botonSalir = findViewById(R.id.)
    }

    fun inicializarListeners(){
        botonCalcJoseLuis.setOnClickListener { calculadoraJoseLuis() }
        botonCalcJorge.setOnClickListener { calculadoraJorge() }
        botonCalcLucia.setOnClickListener { calculadoraLucia() }
        botonIMC.setOnClickListener { calculadoraIMC() }
        botonSalir.setOnClickListener { finish() }
    }

    /**
     * Cambia la pantalla a la calculadora de Jose Luis
     */
    fun calculadoraJoseLuis(){
        val intent = Intent(this,CalcJoseLuisActivity::class.java)
        startActivity(intent)
    }

    /**
     * Cambia la pantalla a la calculadora de Jorge
     */
    fun calculadoraJorge(){
        val intent = Intent(this,CalcJorgeActivity::class.java)
        startActivity(intent)
    }

    /**
     * Cambia la pantalla a la calculadora de Lucia
     */
    fun calculadoraLucia(){
        val intent = Intent(this,CalcLuciaActivity::class.java)
        startActivity(intent)
    }

    /**
     * Cambia la pantalla a la calculadora de IMC
     */
    fun calculadoraIMC(){
        val intent = Intent(this,IMCActivity::class.java)
        startActivity(intent)
    }
}