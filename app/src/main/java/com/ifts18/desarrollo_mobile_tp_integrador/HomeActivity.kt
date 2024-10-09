package com.ifts18.desarrollo_mobile_tp_integrador

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val bienvenido = findViewById<TextView>(R.id.bienvenido)
        val ingresar_datos = findViewById<Button>(R.id.ingresar_datos)
        val historial_comparaciones = findViewById<Button>(R.id.historial_comparaciones)
        val politicas_terminos = findViewById<Button>(R.id.politicas_terminos)

        ingresar_datos.setOnClickListener{ cambiarVista(this, DatosActivity()) }
        historial_comparaciones.setOnClickListener{ cambiarVista(this, HistorialActivity()) }
        politicas_terminos.setOnClickListener{ cambiarVista(this, PoliticasActivity()) }

        // Recuperamos los datos nombre y apellido del archivo de preferencias
        val primera_vez = getSharedPreferences("primera_vez", Context.MODE_PRIVATE)
        val nombre_almacenado = primera_vez.getString("nombre", "")
        val apellido_almacenado = primera_vez.getString("apellido", "")
        val email_almacenado = primera_vez.getString("email", "")

        bienvenido.text = "Bienvenido ${nombre_almacenado} ${apellido_almacenado} \n${email_almacenado}"

        politicas_terminos.setOnClickListener {
            cambiarVista(this, PoliticasActivity())
        }
    }

    private fun cambiarVista(context: Context, activity: AppCompatActivity){
        val intent = Intent(context, activity::class.java )
        startActivity(intent)
        finish()
    }
}