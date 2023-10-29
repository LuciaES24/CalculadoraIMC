package com.lespsan543.calculadoraimc

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.ContextCompat
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.slider.RangeSlider

class IMCActivity : AppCompatActivity() {
    /**
     * Creamos una variable para cada elemento del layout
     *
     * @param altura guarda la altura introducida por el usuario
     * @param peso guarda el peso introducido por el usuario
     * @param edad guarda la edad introducida por el usuario
     * @param genero guarda el genero introducido por el usuario
     * @param resultado guarda el resultado del caálculo del IMC del usuario
     */
    lateinit var botonResultado : Button
    lateinit var botonAtras : Button
    //altura
    var altura: Double = 0.0
    lateinit var alturaNum: TextView
    lateinit var alturaBarra: RangeSlider
    //peso
    var peso: Double = 0.0
    lateinit var pesoNum: TextView
    lateinit var pesoSum: FloatingActionButton
    lateinit var pesoRest: FloatingActionButton
    //edad
    var edad: Int = 0
    lateinit var edadNum: TextView
    lateinit var edadSum: FloatingActionButton
    lateinit var edadRest: FloatingActionButton
    //genero
    var genero: String = ""
    lateinit var masculino: ImageView
    lateinit var femenino: ImageView

    var resultado = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_imcactivity)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        inicializarComponentes()

        inicializarListeners()
    }

    /**
     * Inicializa todos los componentes
     */
    fun inicializarComponentes(){
        botonResultado = findViewById(R.id.botonResultado)
        botonAtras = findViewById(R.id.botonAtrasIMC)
        //altura
        alturaBarra = findViewById(R.id.barra)
        alturaNum = findViewById(R.id.textoAlturaNumero)
        //peso
        pesoNum = findViewById(R.id.textoPesoNumero)
        pesoSum = findViewById(R.id.boton1Sumar)
        pesoRest = findViewById(R.id.boton1Restar)
        //edad
        edadNum = findViewById(R.id.textoEdadNumero)
        edadSum = findViewById(R.id.boton2Sumar)
        edadRest = findViewById(R.id.boton2Restar)
        //genero
        masculino = findViewById(R.id.imagenHombre)
        femenino = findViewById(R.id.imagenMujer)
    }

    /**
     * @param g se le pasa el genero
     * Se guarda en la variable global genero la variable pasada por parametros
     */
    fun elegirGenero(g:String){
        genero = g

        cambiaColorImagen(g)//cambia el color de fondo de la imagen
    }


    /**
     * @param se le pasa el tipo de genero
     * cambia el color de la imagen de fondo si es pulsada
     */
    fun cambiaColorImagen(g:String){
        if(g=="m")
            masculino.setBackgroundColor(ContextCompat.getColor(this,R.color.Oscuro))
        else if(g=="f")
            femenino.setBackgroundColor(ContextCompat.getColor(this,R.color.Oscuro))
    }

    /**
     * @param op se le pasa el tipo de operacion + o -
     * Si es + se suma 1 a la edad
     * Si es - se resta 1 a la edad
     */
    fun elegirEdad(op: String){
        if(op == "+" && edad<112){
            edad++
        }else if(op == "-" && edad>0){
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
        if(op == "+" ){
            peso++
        }else if(op == "-" && peso>0){
            peso--
        }
        pesoNum.text = peso.toInt().toString()
    }

    /**
     * Elige la altura segun los valores del rangeSlider
     */
    fun elegirAltura(valor: Float){
        altura = valor.toDouble()
        alturaNum.text = valor.toInt().toString()
    }


    /**
     * Función para que cada botón realice su función correspondiente al pulsarlo
     */
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
        masculino.setOnClickListener { elegirGenero("m")}
        femenino.setOnClickListener { elegirGenero("f")}
        //altura
        alturaBarra.addOnChangeListener { slider, value, fromUser ->
            elegirAltura(value)
        }

    }

    /**
     * Obtiene el resultado de calcular el IMC
     */
    fun calcularIMC(){
        resultado = peso/(altura*altura)*0.01
    }

    /**
     * Obtiene el cálculo del IMC y pasa a la siguiente activity con el resultado
     */
    fun pulsarCalcular(){
        calcularIMC()
        val intent = Intent(this,IMCResultadoActivity::class.java)
        intent.putExtra("resultado",resultado)
        startActivity(intent)
    }
}