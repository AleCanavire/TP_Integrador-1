package com.ifts18.desarrollo_mobile_tp_integrador

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val titulo = findViewById<TextView>(R.id.titulo)
        val datos_usuario = findViewById<LinearLayout>(R.id.datos_usuario)
        val nombre_usuario = findViewById<EditText>(R.id.nombre)
        val apellido_usuario = findViewById<EditText>(R.id.apellido)
        val email_usuario = findViewById<EditText>(R.id.email)
        val enviar_datos = findViewById<Button>(R.id.enviar)

        val test_inversor = findViewById<LinearLayout>(R.id.test_inversor)
        val edad_opciones = findViewById<RadioGroup>(R.id.edadOpciones)
        val inversion_exp = findViewById<RadioGroup>(R.id.inversionExp)
        val tiempo_inversion = findViewById<RadioGroup>(R.id.tiempo_inversion)
        val enviar_test = findViewById<Button>(R.id.enviar_test)


        val primera_vez = getSharedPreferences("primera_vez", Context.MODE_PRIVATE)
        val es_primera_vez = primera_vez.getBoolean("es_primera_vez", false)

        // Verificamos si es la primera vez que se abre la app
        if (es_primera_vez){
            cambiarVista(this, HomeActivity())
            Toast.makeText(this, "Bienvenido", Toast.LENGTH_SHORT).show()
        }

        // Primer boton que verifica que los campos no esten vacios y avanza a la siguiente pantalla
        enviar_datos.setOnClickListener {
            // Verificamos que los datos no esten vacios
            val nombre = nombre_usuario.text.toString()
            val apellido = apellido_usuario.text.toString()
            val email = email_usuario.text.toString()

            // Verificamos que se haya seleccionado una opcion en cada pregunta
            if (nombre.isEmpty() || apellido.isEmpty() || email.isEmpty()) {
                Toast.makeText(this, "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show()
            } else if (email.isNotEmpty() && !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                Toast.makeText(this, "Por favor, ingrese un email valido", Toast.LENGTH_SHORT).show()
            } else {
                // Cambiamos de vista dentro de la misma pantalla

                //Ocultamos el primer campo y mostramos el segundo
                datos_usuario.visibility = LinearLayout.GONE
                test_inversor.visibility = LinearLayout.VISIBLE
                // Ocultamos el titulo de la pantalla
                titulo.visibility = LinearLayout.GONE
            }
        }

        enviar_test.setOnClickListener {
            // Verificamos que se haya seleccionado una opcion en cada pregunta
            val edad_seleccionada = edad_opciones.checkedRadioButtonId
            val inversion_seleccionada = inversion_exp.checkedRadioButtonId
            val tiempo_seleccionado = tiempo_inversion.checkedRadioButtonId

            // Verificamos que se haya seleccionado una opcion en cada pregunta
            if (edad_seleccionada == -1 || inversion_seleccionada == -1 || tiempo_seleccionado == -1){
                Toast.makeText(this,"Por favor, responda todas las preguntas.", Toast.LENGTH_SHORT).show()
            } else {
                // Guardamos los datos en el archivo de preferencias
                primera_vez.edit().apply{
                    // Cambiamos el estado de primera vez a true
                    putBoolean("es_primera_vez", true)

                    // Guardamos los datos del usuario
                    putString("nombre", nombre_usuario.text.toString())
                    putString("apellido", apellido_usuario.text.toString())
                    putString("email", email_usuario.text.toString())

                    // Aplicamos los cambios
                    apply()
                }

                // Cambiamos de vista
                cambiarVista(this, HomeActivity())
            }
        }
    }

    private fun cambiarVista(context: Context, activity: AppCompatActivity){
        val intent = Intent(context, activity::class.java )
        startActivity(intent)
        finish()
    }
}