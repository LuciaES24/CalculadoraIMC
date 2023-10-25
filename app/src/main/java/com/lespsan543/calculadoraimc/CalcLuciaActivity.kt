package com.lespsan543.calculadoraimc


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate

class CalcLuciaActivity : AppCompatActivity() {
    /**
     * Creamos una variable para cada elemento del layout
     */
    lateinit var textoCalcular : TextView
    lateinit var boton1 : Button
    lateinit var boton2 : Button
    lateinit var boton3 : Button
    lateinit var boton4 : Button
    lateinit var boton5 : Button
    lateinit var boton6 : Button
    lateinit var boton7 : Button
    lateinit var boton8 : Button
    lateinit var boton9 : Button
    lateinit var boton0 : Button
    lateinit var botonDecimal : Button
    lateinit var botonSumar : Button
    lateinit var botonRestar : Button
    lateinit var botonDividir : Button
    lateinit var botonMultiplicar : Button
    lateinit var botonIgual : Button
    lateinit var botonDEL : Button
    lateinit var botonCE : Button

    /**
     * @param calculo objeto de la clase Calculo()
     */
    var calculo = CalculoLucia()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        setContentView(R.layout.activity_main)

        inicializarComponentes()

        inicializarListeners()

    }

    /**
     * Función para inicializar cada componente buscandolo por su id en el layout
     */
    fun inicializarComponentes(){
        textoCalcular = findViewById(R.id.mostrarCalculos)
        boton1 = findViewById(R.id.boton1)
        boton2 = findViewById(R.id.boton2)
        boton3 = findViewById(R.id.boton3)
        boton4 = findViewById(R.id.boton4)
        boton5 = findViewById(R.id.boton5)
        boton6 = findViewById(R.id.boton6)
        boton7 = findViewById(R.id.boton7)
        boton8 = findViewById(R.id.boton8)
        boton9 = findViewById(R.id.boton9)
        boton0 = findViewById(R.id.boton0)
        botonDecimal = findViewById(R.id.decimal)
        botonSumar = findViewById(R.id.botonSumar)
        botonRestar = findViewById(R.id.botonRestar)
        botonMultiplicar = findViewById(R.id.botonmultiplicar)
        botonDividir = findViewById(R.id.botonDividir)
        botonIgual = findViewById(R.id.botonIgual)
        botonDEL = findViewById(R.id.DEL)
        botonCE = findViewById(R.id.botonCE)
    }

    /**
     * Función para que cada botón realice su función correspondiente al pulsarlo
     */
    fun inicializarListeners(){
        boton1.setOnClickListener { calculo.pulsarNumeroODecimal(textoCalcular,"1") }
        boton2.setOnClickListener { calculo.pulsarNumeroODecimal(textoCalcular,"2") }
        boton3.setOnClickListener { calculo.pulsarNumeroODecimal(textoCalcular,"3") }
        boton4.setOnClickListener { calculo.pulsarNumeroODecimal(textoCalcular,"4") }
        boton5.setOnClickListener { calculo.pulsarNumeroODecimal(textoCalcular,"5") }
        boton6.setOnClickListener { calculo.pulsarNumeroODecimal(textoCalcular,"6") }
        boton7.setOnClickListener { calculo.pulsarNumeroODecimal(textoCalcular,"7") }
        boton8.setOnClickListener { calculo.pulsarNumeroODecimal(textoCalcular,"8") }
        boton9.setOnClickListener { calculo.pulsarNumeroODecimal(textoCalcular,"9") }
        boton0.setOnClickListener { calculo.pulsarNumeroODecimal(textoCalcular,"0") }
        botonDecimal.setOnClickListener { calculo.pulsarNumeroODecimal(textoCalcular,".") }
        botonSumar.setOnClickListener { calculo.pulsarSigno(textoCalcular,"+") }
        botonRestar.setOnClickListener { calculo.pulsarSigno(textoCalcular,"-") }
        botonMultiplicar.setOnClickListener { calculo.pulsarSigno(textoCalcular,"x") }
        botonDividir.setOnClickListener { calculo.pulsarSigno(textoCalcular,"/") }
        botonIgual.setOnClickListener { pulsarIgual(textoCalcular) }
        botonDEL.setOnClickListener { calculo.pulsarDEL(textoCalcular) }
        botonCE.setOnClickListener { calculo.pulsarCE(textoCalcular) }
    }

    /**
     * Función que se realizará cuando se pulse el símbolo igual
     *
     * @param textView string que vamos a modificar para mostrar por pantalla
     */
    fun pulsarIgual(textView: TextView){
        //Si se pulsa el igual sin tener los 2 números y la operación se mostrará el mensaje
        if (calculo.num1=="" && calculo.num2=="" && calculo.operacion=="" || calculo.num1=="" && calculo.operacion=="" || calculo.num1==""){
            val toast = Toast.makeText(this,"Debe introducir 2 números y una operación", Toast.LENGTH_SHORT)
            toast.show()
        }//Si está correcto, se realizará el cálculo
        else{
            calculo.operar(textView)
        }
    }
}

/**
 * Función para buscar el último elemento de una cadena
 *
 * @param elemento string del que queremos obtener el último elemento
 */
fun buscarUltimoElemento(elemento:String):String{
    var resultado = ""
    if (elemento.length>0){
        resultado = elemento.substring(elemento.length-1,elemento.length)
    }
    return resultado
}