package com.ifts18.desarrollo_mobile_tp_integrador

import android.content.Context
import android.content.Intent
import android.os.Bundle
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

        val etNombre = findViewById<EditText>(R.id.nombre)
        val etApellido = findViewById<EditText>(R.id.apellido)
        val etMail = findViewById<EditText>(R.id.mail)
        val btEnviar = findViewById<Button>(R.id.botonCampos)
        val btEnviarTest = findViewById<Button>(R.id.botonTest)
        val lnlCampos = findViewById<LinearLayout>(R.id.campos)
        val lnlRespuestas = findViewById<LinearLayout>(R.id.respuestas)
        val rgEdadOpciones = findViewById<RadioGroup>(R.id.edadOpciones)
        val rgInversionExp = findViewById<RadioGroup>(R.id.inversionExp)
        val rgTiempoInversion = findViewById<RadioGroup>(R.id.tiempo_inversion)


        val datosAlmacenados = getSharedPreferences("primera_vez", Context.MODE_PRIVATE)
        val esPrimeraVez = datosAlmacenados.getBoolean("esPrimeraVez", false)

        // Verificamos si es la primera vez que se abre la app
        if (esPrimeraVez){
            cambiarVista(this, HomeActivity())
            Toast.makeText(this, "Bienvenido", Toast.LENGTH_SHORT).show()
        }

        btEnviar.setOnClickListener {
            // Verificamos que los datos no esten vacios
            val nombre = etNombre.text.toString()
            val apellido = etApellido.text.toString()
            val mail = etMail.text.toString()

            // Verificamos que se haya seleccionado una opcion en cada pregunta
            if (nombre.isEmpty() || apellido.isEmpty() || mail.isEmpty()) {
                Toast.makeText(this, "Por favor, complete todos los campos", Toast.LENGTH_SHORT)
                    .show()
            } else {
                // Cambiamos de vista dentro de la misma pantalla
                lnlCampos.visibility = LinearLayout.GONE
                lnlRespuestas.visibility = LinearLayout.VISIBLE
            }
        }

        btEnviarTest.setOnClickListener {
            // Verificamos que se haya seleccionado una opcion en cada pregunta
            val rbSeleccionadoEdad = rgEdadOpciones.checkedRadioButtonId
            val rbSeleccionadoInversion = rgInversionExp.checkedRadioButtonId
            val rbSeleccionadoTiempo = rgTiempoInversion.checkedRadioButtonId

            // Verificamos que se haya seleccionado una opcion en cada pregunta
            if (rbSeleccionadoEdad == -1 || rbSeleccionadoInversion == -1 || rbSeleccionadoTiempo == -1){
                Toast.makeText(this,"Por favor, complete todos los campos", Toast.LENGTH_SHORT).show()
            }else{
                // Guardamos los datos en el archivo de preferencias
                datosAlmacenados.edit().apply{
                    putBoolean("esPrimeraVez", true)
                    putString("nombre", etNombre.text.toString())
                    putString("apellido", etApellido.text.toString())
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