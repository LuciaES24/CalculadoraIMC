package com.lespsan543.calculadoraimc

import android.widget.TextView
import java.text.DecimalFormat

class CalculoLucia {
    /**
     * @param num1 string que guarda el número actual que se está tecleando y guardará el
     * segundo número que se vaya a introducir
     * @param num2 string que guarda el número 1 una vez se ha pulsado un operador
     * @param operacion string que guarda el último operador que se seleccione
     * @param pantalla string que guarda la cadena completa de los botones que se cliqueen
     * @param resultado double que guardará el resultado total de la operación
     */
    var num1=""
    var num2=""
    var operacion = ""
    var pantalla = ""
    var resultado: String = ""

    /**
     * Función para realizar la suma
     *
     * @param num1 string que contiene el segundo número introducido
     * @param num2 string que contiene el primer número introducido
     */
    fun sumar(num1:Double, num2:Double){
        resultado = (num2+num1).toString()
        resultado = redondearResultado(resultado.toDouble())
    }

    /**
     * Función para realizar la resta
     *
     * @param num1 string que contiene el segundo número introducido
     * @param num2 string que contiene el primer número introducido
     */
    fun restar(num1:Double, num2:Double){
        resultado = (num2-num1).toString()
        resultado = redondearResultado(resultado.toDouble())
    }

    /**
     * Función para realizar la multiplicación
     *
     * @param num1 string que contiene el segundo número introducido
     * @param num2 string que contiene el primer número introducido
     */
    fun multiplicar(num1:Double, num2:Double){
        resultado = (num2*num1).toString()
        resultado = redondearResultado(resultado.toDouble())
    }

    /**
     * Función para realizar la división
     *
     * @param num1 string que contiene el segundo número introducido
     * @param num2 string que contiene el primer número introducido
     */
    fun dividir(num1:Double, num2:Double){
        resultado = (num2/num1).toString()
        resultado = redondearResultado(resultado.toDouble())
    }

    /**
     * Función que guardará el número seleccionado cada vez que se pulse o el punto para decimales
     *
     * @param textView string que vamos a modificar para mostrar por pantalla
     * @param numero string con el número o punto que se ha pulsado
     */
    fun pulsarNumeroODecimal(textView: TextView, numero:String){
        //Si el número ya contiene un punto, no puede añadir más
        if (num1.contains(".") && numero=="."){
            num1 = num1
        }else if(numero=="." && num1 == ""){
            num1 = num1
        }
        else{
            num1+=numero
            pantalla +=numero
            textView.text = pantalla
        }
    }

    /**
     * Función que se llevará a cabo cuando se pulse algún signo (+,-,/,x)
     *
     * @param textView string que vamos a modificar para mostrar por pantalla
     * @param signo string de texto en la que se pasará el signo pulsado
     */
    fun pulsarSigno(textView: TextView,signo:String){
        //Si no se ha introducido ningún número
        if(num1 == "" && num2 == ""){
            operacion = ""
        } //Si se pulsa una operación seguida de otra, se sustituirá la primera por la nueva introducida
        else if (operacion!="" && num1 == "" && num2 !=""){
            pantalla = pantalla.substring(0,pantalla.length-1)
            operacion = signo
            pantalla += operacion
            textView.text = pantalla
        }//Si ya se han introducido los 2 números y la operación y se pulsa otra despues del segundo número
        //se muestra el resultado y la nueva operación a la espera de introducir otro número
        else if (operacion != "" && num1 != "" && num2 !=""){
            operar(textView)
            operacion = signo
            pantalla += operacion
            textView.text = pantalla
        }//Si solo se ha introducido num2
        else if(num2!=""){
            operacion = signo
            pantalla += operacion
            textView.text = pantalla
        } //Si se introduce correctamente se guardará la operación con normalidad
        else{
            num2 = num1
            num1 = ""
            operacion = signo
            pantalla += operacion
            textView.text = pantalla
        }
    }

    /**
     * Función que realizará los cálculos según la operación que se haya seleccionado
     *
     * @param textView string que vamos a modificar para mostrar por pantalla
     */
    fun operar(textView: TextView){
        when (operacion){
            "+" -> sumar(num1.toDouble(),num2.toDouble())
            "-" -> restar(num1.toDouble(),num2.toDouble())
            "x" -> multiplicar(num1.toDouble(),num2.toDouble())
            "/" -> dividir(num1.toDouble(),num2.toDouble())
        }
        pantalla = resultado
        num2 = resultado
        num1 = ""
        operacion = ""
        textView.text = pantalla
    }

    /**
     * Función para el botón CE que reiniciará todos los valores
     *
     * @param textView string que vamos a modificar para mostrar por pantalla
     */
    fun pulsarCE(textView: TextView){
        num2 = ""
        num1 = ""
        operacion = ""
        pantalla = ""
        resultado = ""
        textView.text = pantalla
    }

    /**
     * Función para eliminar el último elemento que se ha escrito
     *
     * @param textView string que vamos a modificar para mostrar por pantalla
     */
    fun pulsarDEL(textView: TextView){
        if (pantalla != "" && resultado==""){
            val ultimoElemento = buscarUltimoElemento(pantalla)
            pantalla = pantalla.substring(0,pantalla.length-1)
            //Si el último elemento coincide con el de num1 eliminamos el último elemento de num1
            if (buscarUltimoElemento(num1) == ultimoElemento){
                num1 = num1.substring(0,num1.length-1)
            } //Si el último elemento coincide con el de num2 eliminamos el último elemento de num2
            else if (buscarUltimoElemento(num2) == ultimoElemento){
                num2 = num2.substring(0,num2.length-1)
            } //Si el último elemento coincide con el operador, lo reiniciamos
            else if (operacion == ultimoElemento){
                operacion = ""
                num1 = num2
                num2 = ""
            }
        }else{
            pantalla = pantalla
        }
        textView.text = pantalla
    }

    /**
     * Redondea cualquier número para que tenga solo 2 decimales
     *
     * @param resultado número decimal que queremos redondear
     * @return string con el número redondeado
     */
    fun redondearResultado(resultado:Double) : String{
        val formatoDecimal = DecimalFormat("#.##")
        val cambio = formatoDecimal.format(resultado)
        return cambio.replace(",",".")
    }
}