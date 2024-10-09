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

        //Obtenemos los datos de los campos de texto para la primera inversión
        val monto_1 = findViewById<EditText>(R.id.monto_1).text
        val tasa_interes_1 = findViewById<EditText>(R.id.tasa_interes_1).text
        val plazo_1 = findViewById<EditText>(R.id.plazo_1).text
        val entidad_1 = findViewById<EditText>(R.id.entidad_1).text
        val tipo_inversion_1 = findViewById<Spinner>(R.id.tipo_inversion_1)

        //Obtenemos los datos de los campos de texto para la segunda inversión
        val monto_2 = findViewById<EditText>(R.id.monto_2).text
        val tasa_interes_2 = findViewById<EditText>(R.id.tasa_interes_2).text
        val plazo_2 = findViewById<EditText>(R.id.plazo_2).text
        val entidad_2 = findViewById<EditText>(R.id.entidad_2).text
        val tipo_inversion_2 = findViewById<Spinner>(R.id.tipo_inversion_2)

        //Obtenemos los botones de la vista
        val calcular = findViewById<Button>(R.id.calcular)
        val home = findViewById<Button>(R.id.home)

        //Tipos de inversión en spinners
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

        //Guardamos los datos de historial y cálculo en cache
        val CALCULAR_CONTEXT = getSharedPreferences("CALCULAR_CONTEXT", Context.MODE_PRIVATE)
        val HISTORIAL_CONTEXT = getSharedPreferences("HISTORIAL_CONTEXT", Context.MODE_PRIVATE)

        //Volvemos al Home
        home.setOnClickListener{ cambiarVista(this, HomeActivity()) }

        //Calculamos el rendimiento y lo mostramos en otra vista
        calcular.setOnClickListener{
            if(monto_1.isEmpty() || tasa_interes_1.isEmpty() || plazo_1.isEmpty() || entidad_1.isEmpty() || monto_2.isEmpty() || tasa_interes_2.isEmpty() || plazo_2.isEmpty() || entidad_2.isEmpty()) {
                Toast.makeText(this, "Uno de los campos esta vacio", Toast.LENGTH_LONG).show()
            } else if(monto_1.toString().toInt() < 1500 || monto_1.toString().toInt() > 1000000000 || monto_2.toString().toInt() < 1500 || monto_2.toString().toInt() > 1000000000) {
                Toast.makeText(this, "El monto debe estar entre $1500 y $1.000.000.000", Toast.LENGTH_LONG).show()
            } else if(tasa_interes_1.toString().toInt() < 1 || tasa_interes_1.toString().toInt() > 300 || tasa_interes_2.toString().toInt() < 1 || tasa_interes_2.toString().toInt() > 300){
                Toast.makeText(this, "La tasa de interés debe estar entre 1% y 300%", Toast.LENGTH_LONG).show()
            } else if (plazo_1.toString().toInt() < 30 || plazo_1.toString().toInt() > 365 || plazo_2.toString().toInt() < 30 || plazo_2.toString().toInt() > 365){
                Toast.makeText(this, "El plazo debe estar entre 30 y 365 días", Toast.LENGTH_LONG).show()
            } else {
                CALCULAR_CONTEXT.edit().apply{

                    //Guardamos los datos de cálculo de la inversion 1 en cache
                    putInt("MONTO_1", monto_1.toString().toInt())
                    putInt("TASA_DE_INTERES_1", tasa_interes_1.toString().toInt())
                    putInt("PLAZO_1", plazo_1.toString().toInt())
                    putString("ENTIDAD_1", entidad_1.toString())
                    putString("TIPO_DE_INVERSION_1", tipo_inversion_1.selectedItem.toString())

                    //Guardamos los datos de cálculo de la inversion 2 en cache
                    putInt("MONTO_2", monto_2.toString().toInt())
                    putInt("TASA_DE_INTERES_2", tasa_interes_2.toString().toInt())
                    putInt("PLAZO_2", plazo_2.toString().toInt())
                    putString("ENTIDAD_2", entidad_2.toString())
                    putString("TIPO_DE_INVERSION_2", tipo_inversion_2.selectedItem.toString())

                    //Aplicamos los cambios
                    apply()
                }

                //Incrementamos el número de comparaciones
                val n_comparaciones = HISTORIAL_CONTEXT.getInt("n_comparaciones", 0)
                HISTORIAL_CONTEXT.edit().putInt("n_comparaciones", n_comparaciones + 1).apply()

                //Navegamos a la vista de rendimiento
                cambiarVista(this, RendimientoActivity())
            }
        }
    }

    //Función para cambiar de vista
    private fun cambiarVista(context: Context, activity: AppCompatActivity){
        val intent = Intent(context, activity::class.java )
        startActivity(intent)
        finish()
    }
}