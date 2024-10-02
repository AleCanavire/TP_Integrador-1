package com.ifts18.desarrollo_mobile_tp_integrador

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity

class DatosActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_datos)

        val monto_1 = findViewById<EditText>(R.id.monto_1).text
        val tasa_interes_1 = findViewById<EditText>(R.id.tasa_interes_1).text
        val plazo_1 = findViewById<EditText>(R.id.plazo_1).text
        val entidad_1 = findViewById<EditText>(R.id.entidad_1).text
        val tipo_inversion_1 = findViewById<Spinner>(R.id.tipo_inversion_1)

        val monto_2 = findViewById<EditText>(R.id.monto_2).text
        val tasa_interes_2 = findViewById<EditText>(R.id.tasa_interes_2).text
        val plazo_2 = findViewById<EditText>(R.id.plazo_2).text
        val entidad_2 = findViewById<EditText>(R.id.entidad_2).text
        val tipo_inversion_2 = findViewById<Spinner>(R.id.tipo_inversion_2)

        val calcular = findViewById<Button>(R.id.calcular)
        val home = findViewById<Button>(R.id.home)

        ArrayAdapter.createFromResource(
            this, R.array.tipos_de_inversion, android.R.layout.simple_spinner_item
        ).also { adapter -> adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            tipo_inversion_1.adapter = adapter
        }
        ArrayAdapter.createFromResource(
            this, R.array.tipos_de_inversion, android.R.layout.simple_spinner_item
        ).also { adapter -> adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            tipo_inversion_2.adapter = adapter
        }

        home.setOnClickListener{ cambiarVista(this,MainActivity()) }

    }
    private fun cambiarVista(context: Context, activity: AppCompatActivity){
        val intent = Intent(context, activity::class.java )
        startActivity(intent)
        finish()
    }
}