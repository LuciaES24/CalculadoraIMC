package com.lespsan543.calculadoraimc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView




class CalcJoseLuisActivity : AppCompatActivity() {

    private lateinit var botonAtras : Button
    private lateinit var pantalla: TextView
    private lateinit var boton0: Button
    private lateinit var boton1: Button
    private lateinit var boton2: Button
    private lateinit var boton3: Button
    private lateinit var boton4: Button
    private lateinit var boton5: Button
    private lateinit var boton6: Button
    private lateinit var boton7: Button
    private lateinit var boton8: Button
    private lateinit var boton9: Button
    private lateinit var punto:Button
    private lateinit var reseteo: Button
    private lateinit var suma: Button
    private lateinit var resta: Button
    private lateinit var multiplicacion: Button
    private lateinit var division: Button
    private lateinit var igual: Button
    private lateinit var borrar:Button
    private lateinit var cal: CalculoJoseLuis
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calc_jose_luis)

        cal = CalculoJoseLuis()

        inicializarComponentes()

    }
    fun inicializarComponentes(){
        botonAtras = findViewById(R.id.botonAtrasCalcjoseluis)
        pantalla = findViewById(R.id.pantalla)
        boton0 = findViewById(R.id.num0)
        boton1 = findViewById(R.id.num1)
        boton2 = findViewById(R.id.num2)
        boton3 = findViewById(R.id.num3)
        boton4 = findViewById(R.id.num4)
        boton5 = findViewById(R.id.num5)
        boton6 = findViewById(R.id.num6)
        boton7 = findViewById(R.id.num7)
        boton8 = findViewById(R.id.num8)
        boton9 = findViewById(R.id.num9)
        punto = findViewById(R.id.btnDec)
        reseteo = findViewById(R.id.resetear)
        suma = findViewById(R.id.suma)
        resta = findViewById(R.id.resta)
        multiplicacion = findViewById(R.id.multiplicacion)
        division = findViewById(R.id.division)
        igual = findViewById(R.id.resultado)
        borrar = findViewById(R.id.borrar)
    }
}