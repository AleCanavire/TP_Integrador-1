package com.ifts18.desarrollo_mobile_tp_integrador

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlin.math.log

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bienvenido = findViewById<TextView>(R.id.bienvenido)
        val ingresar_datos = findViewById<Button>(R.id.ingresar_datos)
        val calcular_rendimiento = findViewById<Button>(R.id.calcular_rendimiento)
        val historial_comparaciones = findViewById<Button>(R.id.historial_comparaciones)
        val politicas_terminos = findViewById<Button>(R.id.politicas_terminos)

        ingresar_datos.setOnClickListener{ cambiarVista(this, DatosActivity()) }
        calcular_rendimiento.setOnClickListener{ cambiarVista(this, RendimientoActivity()) }
        historial_comparaciones.setOnClickListener{ cambiarVista(this, HistorialActivity()) }
        politicas_terminos.setOnClickListener{ cambiarVista(this, PoliticasActivity()) }
    }

    private fun cambiarVista(context: Context, activity: AppCompatActivity){
        val intent = Intent(context, activity::class.java )
        startActivity(intent)
        finish()
    }
}