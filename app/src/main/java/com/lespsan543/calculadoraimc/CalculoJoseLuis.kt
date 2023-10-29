package com.lespsan543.calculadoraimc

/**
 * @author jose luis garcia zamora
 */
class CalculoJoseLuis {
    var num1 = ""
    var num2 = ""
    var operacion = ""
    var resultado = 0

    /**
     * @param num1 pasamos el num1 como int para que se pueda hacer la operacion
     * @param num2 pasamos el num2 como int para que se pueda hacer la operacion
     * Función que cuando le pasas por parametro dos numeros te hace una suma
     */

    fun suma(num1:Int, num2:Int){
        resultado=num1+num2
    }

    /**
     * @param num1 pasamos el num1 como int para que se pueda hacer la operacion
     * @param num2 pasamos el num2 como int para que se pueda hacer la operacion
     * Función que cuando le pasas por parametro dos numeros te hace una suma
     */

    fun resta(num1:Int, num2:Int){
        resultado=num1-num2
    }

    /**
     * @param num1 pasamos el num1 como int para que se pueda hacer la operacion
     * @param num2 pasamos el num2 como int para que se pueda hacer la operacion
     * Función que cuando le pasas por parametro dos numeros te hace una multiplicacion
     */

    fun multiplicacion(num1:Int, num2:Int){
        resultado=num1*num2
    }

    /**
     * @param num1 el num1 como int para que se pueda hacer la operacion
     * @param num2 el num2 como int para que se pueda hacer la operacion
     * Función que cuando le pasas por parametro dos numeros te hace una division
     */

    fun division(num1:Int, num2:Int){
        resultado=num1/num2
    }

    /**
     * Función que cuando pulsas el boton CE se resetee las variables a como estaban por defecto
     */

    fun reset(){
        num1 = ""
        num2 = ""
        resultado = 0
        operacion = ""
    }
}