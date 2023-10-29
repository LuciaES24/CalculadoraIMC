package com.jorgearceruiz97.calculadora

class CalculoJorge {
    //los atributos en kotlin no tiene get y set
    var num1 = 0
    var num2 = 0
    var resultado = 0
    var operacion = 0

    var primerNum = true // true -> esperando num1 / false -> esperando num2
    var num1Temp = ""
    var num2Temp = ""
    var numCalculos = 0


    /**
     * realiza la llamada al método adecuado para realizar el cálculo solicitado a una calculadora
     * si el num1Temp y el num2Temp no están vacios los cambia a enteros y realiza una operación
     * segun el número que tenga almacenado
     * si no el resultado es cero
     */
    fun calcular(){
        if (num1Temp.isNotEmpty() && num2Temp.isNotEmpty()) {
            num1 = num1Temp.toInt()
            num2 = num2Temp.toInt()

            when (operacion) {
                0 -> suma()
                1 -> resta()
                2 -> mult()
                3 -> div()
            }
            numCalculos++
        } else {
            resultado = -1 //no te devuelve ninguna operacion
        }
    }

    /**
     * resetea el primerNum a true y los num1 y 2 y numeros temporales
     */
    fun resetear(){
        primerNum = true
        num1 = 0
        num2 = 0
        num1Temp = ""
        num2Temp = ""
    }

    /**
     * establece numero temporal, si el primerNum ha sido introducido se modificara el segundo numTemporal
     * @param numero que se va a añadir como numero temporal
     */
    fun establecerNumero(numero: Int) {
        if (primerNum) {
            num1Temp += numero
        } else {
            num2Temp += numero
        }
    }

    /**
     * establece la operacion, cambia el primer num a false ya que se prevee que una vez
     * se introduzca una operación el siguiente num será el num2
     * @param op equivale a la operacion que va a realizar el usuario
     */
    fun establecerOperacion(op: Int) {
        operacion = op
        primerNum = false
    }

    private fun suma() {
        resultado = num1 + num2
    }

    private fun resta() {
        resultado = num1 - num2
    }

    private fun mult() {
        resultado = num1 * num2
    }

    private fun div() {
        resultado = if (num2 != 0) {
            num1 / num2
        } else {
            0
        }
    }

    /**
     * obtiene el resultado
     * @return resultado que se obtiene al realizar una operación
     */
    fun obtenerResultado(): Int {
        return resultado
    }

    /**
     * Actualiza y almacena la información en cadenas de caracteres (this.numTemp1 y this.numTemp2) de cada pulsación de los dígitos de los números que se usarán posteriormente en el cálculo y acabarán siendo almacenados como this.num1 y this.num2.
     *
     * @param num que corresponde al dígito del 0 al 9 pulsado o al punto decimal (10)
     */
    fun tecleaDigito(num : Int) {
        //Si es menor que 10, se trata de un dígito del 0 al 9.
        //Sino, es el punto decimal.
        if (num < 10){
            if (this.primerNum) this.num1Temp += num.toString()
            else this.num2Temp += num.toString()
        }
        else {
            if (this.primerNum) {
                //Si no se tecleo ningún dígito antes del punto decimal, establecemos el valor 0.
                //Sino, lo agregamos a la cadena siempre que no exista ya con anterioridad
                if (this.num1Temp == "") this.num1Temp = "0."
                else this.num1Temp += if (this.num1Temp.contains('.')) "" else "."
            }
            else {
                //Mismas acciones, pero con la cadena que va recogiendo el número 2
                if (this.num2Temp == "") this.num2Temp = "0."
                else this.num2Temp += if (this.num2Temp.contains('.')) "" else "."
            }

        }
    }

}