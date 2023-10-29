package com.lespsan543.calculadoraimc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast


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

        inicializarListeners()
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
    fun inicializarListeners() {
        boton0.setOnClickListener { pulsarBoton("0") }
        boton1.setOnClickListener { pulsarBoton("1") }
        boton2.setOnClickListener { pulsarBoton("2") }
        boton3.setOnClickListener { pulsarBoton("3") }
        boton4.setOnClickListener { pulsarBoton("4") }
        boton5.setOnClickListener { pulsarBoton("5") }
        boton6.setOnClickListener { pulsarBoton("6") }
        boton7.setOnClickListener { pulsarBoton("7") }
        boton8.setOnClickListener { pulsarBoton("8") }
        boton9.setOnClickListener { pulsarBoton("9") }
        division.setOnClickListener { operacion("/") }
        suma.setOnClickListener { operacion("+") }
        resta.setOnClickListener { operacion("-") }
        punto.setOnClickListener{pulsarBoton(".")}
        multiplicacion.setOnClickListener { operacion("x") }
        igual.setOnClickListener { igual() }
        reseteo.setOnClickListener { reseteo() }
        borrar.setOnClickListener { borrar() }
        botonAtras.setOnClickListener { onBackPressed() }
    }
    /**
     * En la funcion pulsarBoton uso la logica de que si no tiene operacion el numero que estoy poniendo se guarda en el num1
     * si no se guardara en el num2
     */

    private fun pulsarBoton(numero: String) {
        if (numero == ".") {
            if (!cal.num1.contains(".") && cal.operacion.isEmpty()) {
                cal.num1 += numero
                pantalla.text = cal.num1
            } else if (!cal.num2.contains(".") && cal.num1.isNotEmpty() && cal.operacion.isNotEmpty()) {
                cal.num2 += numero
                pantalla.text = cal.num2
            }
        } else {
            if (cal.operacion.isEmpty()) {
                cal.num1 += numero
                pantalla.text = cal.num1
            } else {
                cal.num2 += numero
                pantalla.text = cal.num2
            }
        }
    }
    /**
     * @param signo se le pasa la operacion que quieras hacer por parametro
     * funcion que sirve para cuando el numero 1 no esta vacion y el dos no dices cual es el signo si vuelves a pulsar otro signo se reemplaza ya que num2 sigue vacio
     * si los dos numeros ya estan introducidos y le das igual llama a la funcion resultado para que guarde el resultado y vuelve a dejar num2 vacio
     */

    private fun operacion(signo: String) {
        if (cal.num1.isNotEmpty() && cal.num2.isEmpty()) {
            cal.operacion = signo
        } else if (cal.num1.isNotEmpty() && cal.num2.isNotEmpty()) {
            resultado()
            cal.num1 = cal.resultado.toString()
            cal.num2 = ""
            cal.operacion = signo
        }
    }

    /**
     * funcion para borrar el ultimo dijito y la operacion
     */
    fun borrar() {
        if (cal.operacion.isEmpty() && cal.num1.isNotEmpty()) {
            // Si no hay operación y num1 no está vacío, borra el último dígito de num1
            cal.num1 = cal.num1.dropLast(1)
            pantalla.text = cal.num1
        } else if (cal.operacion.isNotEmpty() && cal.num2.isNotEmpty()) {
            // Si hay una operación y num2 no está vacío, borra el último dígito de num2
            cal.num2 = cal.num2.dropLast(1)
            pantalla.text = cal.num2
        } else if (cal.num1.isNotEmpty() && cal.operacion.isNotEmpty() && cal.num2.isEmpty()) {
            // Si hay una operación, num1 no está vacío, y num2 está vacío, borra la operación
            cal.operacion = ""
            pantalla.text = cal.num1
        } else {
            // Si no hay nada para borrar, muestra un mensaje de error
            mensajeError("Nada para borrar")
        }
    }


    /**
     * Funcion igual para cuando pulsas el boton igual y el num1 y num2 y operacion no esta vacia se hace el resultado y para que despues del igual puedas hacer otra operacion
     * a num2 y operacion se le vuleve a dejar vacios para que se pueda hacer otra operacion y el ese es por si no introduce num 2 o la operacion antes de darle al igual te salga un
     * toast que ter dice que debes de introducir dos numero
     */

    private fun igual() {
        if (cal.num1.isNotEmpty() && cal.num2.isNotEmpty() && cal.operacion.isNotEmpty()) {
            resultado()
            pantalla.text = cal.resultado.toString()
            cal.num1 = cal.resultado.toString()
            cal.num2 = ""
            cal.operacion = ""
        }else{
           mensajeError("Debe introducir 2 números y una operación")
        }
    }

    /**
     * funcion para resetear las variables de la clase Calculo
     */

    private fun reseteo() {
        cal.reset()
        pantalla.text = "0"
    }

    /**
     * Funcion para saber que funcion de la clase Calculo se realiza dependiendo de la operacion que tenga guardada y
     * como num1 y num2 son String lo tienes que pasar a int con toInt()
     */

    private fun resultado() {
        when (cal.operacion) {
            "+" -> cal.suma(cal.num1.toDouble(), cal.num2.toDouble())
            "-" -> cal.resta(cal.num1.toDouble(), cal.num2.toDouble())
            "x" -> cal.multiplicacion(cal.num1.toDouble(), cal.num2.toDouble())
            "/" -> cal.division(cal.num1.toDouble(), cal.num2.toDouble())
        }
    }
    private fun mensajeError(msj: String) {
        Toast.makeText(this, msj, Toast.LENGTH_SHORT).show()
    }
}