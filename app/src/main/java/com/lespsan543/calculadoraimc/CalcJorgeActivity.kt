package com.lespsan543.calculadoraimc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.jorgearceruiz97.calculadora.CalculoJorge
import java.text.DecimalFormat

class CalcJorgeActivity : AppCompatActivity() {

    private lateinit var pantalla : TextView


    private lateinit var numeros : ArrayList<Button>
    private lateinit var operaciones : ArrayList<Button>


    private lateinit var buttonCE : Button
    private lateinit var buttonResultado : Button
    private lateinit var buttonBorrar : Button


    private lateinit var calc : CalculoJorge
    private val df = DecimalFormat("#.##")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calc_jorge)

        calc = CalculoJorge()


        //inicializamos los componentes
        iniciarComponentes()
        iniciarListeners()
    }

    /**
     * Inicializar las variables que se asignarán a cada componente que vamos a controlar.
     */
    private fun iniciarComponentes(){
        pantalla = findViewById(R.id.screen)

        buttonCE = findViewById(R.id.CE)
        buttonResultado = findViewById(R.id.buttonIgual)
        buttonBorrar = findViewById(R.id.buttonBorrar)

        pantalla.text = ""

        iniciarBtnNumeros()//inicializa los botones de numeros
        iniciarBtnOperaciones()//inicializa los botones de operaciones
    }

    /**
     * Establecer las variables del ArraList numeros que controlarán los dígitos y el punto decimal.
     */
    private fun iniciarBtnNumeros(){
        numeros = ArrayList()
        numeros.add(findViewById(R.id.button0))
        numeros.add(findViewById(R.id.button1))
        numeros.add(findViewById(R.id.button2))
        numeros.add(findViewById(R.id.button3))
        numeros.add(findViewById(R.id.button4))
        numeros.add(findViewById(R.id.button5))
        numeros.add(findViewById(R.id.button6))
        numeros.add(findViewById(R.id.button7))
        numeros.add(findViewById(R.id.button8))
        numeros.add(findViewById(R.id.button9))
        numeros.add(findViewById(R.id.buttonDecimal))
    }

    /**
     * Guarda las variables en el ArrayList btnOperaciones que realizan los calculos
     */
    private fun iniciarBtnOperaciones(){
        operaciones = ArrayList()
        operaciones.add(findViewById(R.id.buttonSum))
        operaciones.add(findViewById(R.id.buttonRest))
        operaciones.add(findViewById(R.id.buttonMult))
        operaciones.add(findViewById(R.id.buttonDiv))
    }

    /**
     * Establecer los eventos y las funciones asociadas de cada componente que vamos a controlar.
     */
    private fun iniciarListeners(){
        for (i in 0..<numeros.count()){
            numeros[i].setOnClickListener{ btnNumClicked(i) }
        }

        for (i in 0..<operaciones.count()){
            operaciones[i].setOnClickListener{ btnOperClicked(i) }
        }

        buttonCE.setOnClickListener{ btnCEClicked() }
        buttonResultado.setOnClickListener{ btnResultClicked() }
        buttonBorrar.setOnClickListener{btnBorrarClicked()}

    }

    /**
     * Metodo que borra el número que se está introduciendo
     * Primero comprobamos que numero es el que se esta introduciendo o está por pantalla con el booleano
     * calc.primerNum se comprueba eso, luego modificamos el número
     */
    private fun btnBorrarClicked() {
        if(calc.primerNum){
            if(calc.numTemp1.isNotEmpty()){//si el numTemp 1 no está vacío
                calc.numTemp1 = calc.numTemp1.substring(0,calc.numTemp1.length-1) //le quita un digito al num
                muestraValor(calc.numTemp1,calc.numTemp1) //muestra en pantalla y en detalle el numero alterado
            }else{
                mensajeError("No existe nada para borrar")
            }
        }else{
            if(calc.numTemp2.isNotEmpty()){
                calc.numTemp2 = calc.numTemp2.substring(0,calc.numTemp2.length-1)
                muestraValor(calc.numTemp2,calc.numTemp1 + calc.operadorTxt() + calc.numTemp2)
            } else if (calc.operacion != 0) {
                calc.operacion = 0 //si el calc.op es diferente a cero lo iguala a 0
                muestraValor(calc.numTemp1, calc.numTemp1) // muestra el valor en la pantalla y el detalle de numTemp1
            } else {
                calc.primerNum = true // si el numTemp2 está vacio y ademas el operador el 0 cambia a true el primerNum y entonces va al primer if de cal.primerNum = true
                muestraValor(calc.numTemp1, calc.numTemp1) //muestra el valor en la pantalla y el detalle de numTemp1
            }
        }
    }

    /**
     * Agrega el dígito pulsado en el número correspondiente del objeto calc.
     *
     *
     * @param num dígito pulsado del 0 al 9 o punto decimal (10)
     */
    private fun btnNumClicked(num : Int){
        calc.tecleaDigito(num)

        //Mostramos info actualizada en los TextView de la app
        if (calc.primerNum) {
            muestraValor(calc.numTemp1, calc.numTemp1)
        }
        else {
            muestraValor(calc.numTemp2, calc.numTemp1 + calc.operadorTxt() + calc.numTemp2)
        }
    }

    /**
     * Agrega la operación del cálculo a realizar
     *
     * @param num operación (0 -> + / 1 -> - / 2 -> * / 3 -> /
     */
    private fun btnOperClicked(num : Int){
        if (calc.primerNum) {
            //Tratamiento de la operación cuando estamos introduciendo el primer número.

            if (calc.numCalculos > 0 && calc.numTemp1 == "") {
                //Si hay un cálculo anterior y el num1 aún está vacío, el resultado anterior es el num1 del siguiente cálculo.
                calc.num1 = calc.resultado.toFloat()
                calc.numTemp1 = df.format(calc.resultado).toString()
            }
            else {
                //Sino, asignamos num1 del objeto calc convirtiendo los dígitos introducidos a float.
                //Además, si existe algún problema o cuando si se pulsa un operador sin introducir número antes, lo capturamos y usamos el valor 0.
                try {
                    calc.num1 = calc.numTemp1.toFloat().toFloat()
                } catch (e: NumberFormatException) {
                    calc.num1 = 0.0F
                    calc.numTemp1 = "0"
                }
            }

            //Asignamos el operador al objeto calc, mostramos info en pantalla y actualizamos las características necesarias de calc para indicar que pasamos al estado de introducir el segundo número.
            calc.operacion = num
            muestraValor(calc.operadorTxt(), calc.numTemp1 + calc.operadorTxt())
            calc.numTemp2 = ""
            calc.primerNum = false
        }
        else if (calc.numTemp2 == "") {
            //Si se introduce una operación y aún no existe el segundo número la nueva operación debe reemplazar la operación anterior.

            calc.operacion = num
            //Mostramos en pantalla la actualización del operador.
            muestraValor(calc.operadorTxt(), calc.numTemp1 + calc.operadorTxt())
        }
        else {
            //Tratamiento de la operación cuando estamos introduciendo el segundo número.

            //Convertimos la cadena de dígitos en el número 2 y realizamos el cálculo.
            //Si existe algún problema en la conversión la controlamos asignando el valor 0.
            calc.num2 = try { calc.numTemp2.toFloat() } catch (e: NumberFormatException) { 0f }
            calc.calcular()

            //Mostramos en pantalla el resultado del cálculo como detalle y la operación en la pantalla principal.
            muestraValor(calc.operadorTxt(num), df.format(calc.resultado).toString() + calc.operadorTxt(num))

            //Actualizamos las características necesarias del objeto calc, ya que vamos a seguir en el estado de introducir solo un segundo número, ya que el primer número y la operación es asignado como el resultado del cálculo realizado y la nueva operación introducida.
            calc.num1 = calc.resultado
            calc.operacion = num
            calc.num2 = 0f
            calc.numTemp1 = df.format(calc.num1).toString()
            calc.numTemp2 = ""
        }
    }

    /**
     * Reiniciar las características del objeto calc cuando se pulsa el botón CE
     */
    private fun btnCEClicked() {
        //Mostramos en pantalla y detalle la cadena de caracteres vacía.
        muestraValor("", "")

        //Inicializamos las características del objeto calc.
        calc.iniValores()
    }

    /**
     * Acciones a realizar al pulsar el botón =.
     * Solo se ejecutará si estamos introduciendo el segundo número.
     * Realizará las acciones necesarias para mostrar el cálculo en pantalla.
     */
    private fun btnResultClicked(){
        if (!calc.primerNum && calc.numTemp2 != ""){
            //Si estamos introduciendo el segundo número, lo actualizamos convirtiendo la cadena de dígitos y calculamos la operación.
            calc.num2 = try { calc.numTemp2.toFloat() } catch (e: NumberFormatException) { 0f }
            calc.calcular()

            //Mostramos en pantalla el resultado y en detalle toda la operación (num1 + num2 = result) formateando a 2 posiciones decimales.
            muestraValor(df.format(calc.resultado).toString(), df.format(calc.num1).toString() + calc.operadorTxt() + df.format(calc.num2).toString() + "=" + df.format(calc.resultado).toString())

            //Inicializamos las características del objeto calc, excepto el núemro de cálculos.
            calc.iniValores(resetNumCalculos = false, resetResult = false)
        }
        else {
            mensajeError("Debe introducir 2 números y una operación para mostrar un resultado")
        }
    }

    /**
     * Muestra la información en los componentest TextView txtPantalla y txtDetalle.
     *
     * @param pantalla info a mostrar en txtPantalla
     * @param detalle info a mostrar en txtDetalle
     */
    private fun muestraValor(pantalla : String, detalle : String){
        this.pantalla.text = getString(R.string.pantalla, pantalla)
    }


    /**
     * Muestra un mensaje de error en pantalla durante un tiempo corto.
     *
     * @msj mensaje de error
     */
    private fun mensajeError(msj: String) {
        Toast.makeText(this, msj, Toast.LENGTH_SHORT).show()
    }
}