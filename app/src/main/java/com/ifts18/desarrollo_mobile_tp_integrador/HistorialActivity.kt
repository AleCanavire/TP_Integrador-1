package com.ifts18.desarrollo_mobile_tp_integrador

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.marginBottom

class HistorialActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_historial)

        val historial_comparaciones = findViewById<LinearLayout>(R.id.historial_comparaciones)
        val home = findViewById<Button>(R.id.home)
        home.setOnClickListener{ cambiarVista(this, HomeActivity()) }

        //Obtenemos el número de comparaciones
        val HISTORIAL_CONTEXT = getSharedPreferences("HISTORIAL_CONTEXT", Context.MODE_PRIVATE)
        val n_comparaciones = HISTORIAL_CONTEXT.getInt("n_comparaciones", 0)

        //Mostramos las ultimas 5 comparaciones
        for (i in n_comparaciones downTo n_comparaciones-4){
            val textview_comparacion = TextView(this)

            val comparacion = HISTORIAL_CONTEXT.getString("comparacion_$i", "")

            // Agregamos estilos a los TextView desde el .kt
            val params = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, // Width
                LinearLayout.LayoutParams.WRAP_CONTENT  // Height
            )
            params.setMargins(16, 16, 16, 16)
            textview_comparacion.layoutParams = params
            textview_comparacion.setPadding(40,40,40,40)
            textview_comparacion.textSize = 18f
            textview_comparacion.setTextColor(Color.parseColor("#ffffff"))
            textview_comparacion.setBackgroundColor(Color.parseColor("#057EA3"))

            textview_comparacion.text = comparacion

            if (i > 0) {
                historial_comparaciones.addView(textview_comparacion)
            }
        }
    }

    private fun cambiarVista(context: Context, activity: AppCompatActivity){
        val intent = Intent(context, activity::class.java )
        startActivity(intent)
        finish()
    }
}