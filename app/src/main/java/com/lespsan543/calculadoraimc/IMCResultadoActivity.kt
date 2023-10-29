package com.lespsan543.calculadoraimc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatDelegate
import java.text.DecimalFormat

class IMCResultadoActivity : AppCompatActivity() {
    /**
     * Variables para cada componente del layout
     */
    lateinit var mostrarResultado : TextView
    lateinit var imagenResultado : ImageView
    lateinit var mostrarInfo : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_imcresultado)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        /**
         * @param resultadoObtenido double obtenido del activity IMCActivity con el resultado del cálculo
         */
        val intent = intent.extras
        val resultadoObtenido = intent?.getDouble("resultado")

        inicializarComponentes()

        mostrarResultadoEImagen(resultadoObtenido)
    }

    /**
     * Inicializa cada componente buscandolo por su id en el layout
     */
    fun inicializarComponentes(){
        mostrarResultado = findViewById(R.id.mostrarResultado)
        imagenResultado = findViewById(R.id.imagenResultado)
        mostrarInfo = findViewById(R.id.mostrarInfo)
    }

    /**
     * Muestra el texto con el resultado y la imagen dependiendo de la cifra obtenida
     *
     * @param resultadoObtenido double con el resultado de IMC
     */
    fun mostrarResultadoEImagen(resultadoObtenido : Double?){
        val resultado = redondearResultado(resultadoObtenido)
        if (resultado < 18.5){
            val id = getResources().getIdentifier(R.drawable.imcbajo.toString(),null,null)
            imagenResultado.setImageResource(id)
            mostrarInfo.text = "Su índice es bajo"
        }else if(resultado >= 18.5 && resultado<=24.9){
            val id = getResources().getIdentifier(R.drawable.imcnormal.toString(),null,null)
            imagenResultado.setImageResource(id)
            mostrarInfo.text = "Su índice es normal"
        }else if(resultado >= 25 && resultado<= 29.9){
            val id = getResources().getIdentifier(R.drawable.imcalto.toString(),null,null)
            imagenResultado.setImageResource(id)
            mostrarInfo.text = "Su índice es superior al normal"
        }else if(resultado>29.9){
            val id = getResources().getIdentifier(R.drawable.imcmuyalto.toString(),null,null)
            imagenResultado.setImageResource(id)
            mostrarInfo.text = "Su índice es bastante superior al normal"
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