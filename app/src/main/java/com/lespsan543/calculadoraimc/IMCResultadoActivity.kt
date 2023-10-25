package com.lespsan543.calculadoraimc

import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import java.text.DecimalFormat

class IMCResultadoActivity : AppCompatActivity() {
    /**
     * Variables para cada componente del layout
     */
    lateinit var mostrarResultado : TextView
    lateinit var imagenResultado : ImageView

    /**
     * @param resultadoObtenido double obtenido del activity IMCActivity con el resultado del cálculo
     */
    val resultadoObtenido = intent.extras?.getDouble("resultado")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_imcresultado)

        inicializarComponentes()

    }

    /**
     * Inicializa cada componente buscandolo por su id en el layout
     */
    fun inicializarComponentes(){
        mostrarResultado = findViewById(R.id.mostrarResultado)
        imagenResultado = findViewById(R.id.imagenResultado)
    }

    /**
     * Muestra el texto con el resultado y la imagen dependiendo de la cifra obtenida
     */
    fun mostrarResultadoEImagen(){
        val resultado = redondearResultado(resultadoObtenido)
        if (resultado < 18.5){

        }else if(resultado >= 18.5 && resultado<=24.9){

        }else if(resultado >= 25 && resultado<= 29.9){

        }else if(resultado>29.9){

        }
        mostrarResultado.text = "Tu resultado es de $resultado"
    }
}

/**
 * Redondea cualquier número para que tenga solo 1 decimal
 *
 * @param resultado número decimal que queremos redondear
 * @return double con el número redondeado
 */
fun redondearResultado(resultado:Double?) : Double{
    val formatoDecimal = DecimalFormat("#.#")
    val cambio = formatoDecimal.format(resultado).toDouble()
    return cambio
}