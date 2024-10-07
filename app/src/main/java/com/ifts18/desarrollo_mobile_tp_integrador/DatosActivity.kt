package com.ifts18.desarrollo_mobile_tp_integrador

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
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

        val CALCULAR_CONTEXT = getSharedPreferences("CALCULAR_CONTEXT", Context.MODE_PRIVATE)

        val HISTORIAL_CONTEXT = getSharedPreferences("HISTORIAL_CONTEXT", Context.MODE_PRIVATE)

        home.setOnClickListener{ cambiarVista(this, HomeActivity()) }

        calcular.setOnClickListener{

            if(monto_1.isNotEmpty() && tasa_interes_1.isNotEmpty() && plazo_1.isNotEmpty() && entidad_1.isNotEmpty()){
                CALCULAR_CONTEXT.edit().apply{
                    putInt("MONTO_1", monto_1.toString().toInt())
                    putInt("TASA_DE_INTERES_1", tasa_interes_1.toString().toInt())
                    putInt("PLAZO_1", plazo_1.toString().toInt())
                    putString("ENTIDAD_1", entidad_1.toString())
                    putString("TIPO_DE_INVERSION_1", tipo_inversion_1.selectedItem.toString())

                    putInt("MONTO_2", monto_2.toString().toInt())
                    putInt("TASA_DE_INTERES_2", tasa_interes_2.toString().toInt())
                    putInt("PLAZO_2", plazo_2.toString().toInt())
                    putString("ENTIDAD_2", entidad_2.toString())
                    putString("TIPO_DE_INVERSION_2", tipo_inversion_2.selectedItem.toString())

                    apply()
                }

                val n_comparaciones = HISTORIAL_CONTEXT.getInt("n_comparaciones", 0)
                HISTORIAL_CONTEXT.edit().putInt("n_comparaciones", n_comparaciones + 1).apply()

                cambiarVista(this, RendimientoActivity())
            } else {
                Toast.makeText(this, "Uno de los campos esta vacio", Toast.LENGTH_LONG).show()
            }
        }

    }
    private fun cambiarVista(context: Context, activity: AppCompatActivity){
        val intent = Intent(context, activity::class.java )
        startActivity(intent)
        finish()
    }
}