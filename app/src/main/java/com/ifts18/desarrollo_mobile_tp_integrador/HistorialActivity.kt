package com.ifts18.desarrollo_mobile_tp_integrador

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class HistorialActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_historial)

        val historial_comparaciones = findViewById<LinearLayout>(R.id.historial_comparaciones)
        val home = findViewById<Button>(R.id.home)
        home.setOnClickListener{ cambiarVista(this, HomeActivity()) }

        val HISTORIAL_CONTEXT = getSharedPreferences("HISTORIAL_CONTEXT", Context.MODE_PRIVATE)
        val n_comparaciones = HISTORIAL_CONTEXT.getInt("n_comparaciones", 0)

        for (i in 1..n_comparaciones){
            val textview_comparacion = TextView(this)

            val comparacion = HISTORIAL_CONTEXT.getString("comparacion_$i", "")
            textview_comparacion.setPadding(0,0,0,20)
            textview_comparacion.text = comparacion

            historial_comparaciones.addView(textview_comparacion)
        }
    }

    private fun cambiarVista(context: Context, activity: AppCompatActivity){
        val intent = Intent(context, activity::class.java )
        startActivity(intent)
        finish()
    }
}