package com.ifts18.desarrollo_mobile_tp_integrador

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class RendimientoActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rendimiento)

        val entidad_1 = findViewById<TextView>(R.id.entidad_1)
        val tipo_inversion_1 = findViewById<TextView>(R.id.tipo_inversion_1)
        val tna_1 = findViewById<TextView>(R.id.tna_1)
        val capital_inicial_1 = findViewById<TextView>(R.id.capital_inicial_1)
        val capital_final_1 = findViewById<TextView>(R.id.capital_final_1)
        val rendimiento_1 = findViewById<TextView>(R.id.rendimiento_1)

        val entidad_2 = findViewById<TextView>(R.id.entidad_2)
        val tipo_inversion_2 = findViewById<TextView>(R.id.tipo_inversion_2)
        val tna_2 = findViewById<TextView>(R.id.tna_2)
        val capital_inicial_2= findViewById<TextView>(R.id.capital_inicial_2)
        val capital_final_2 = findViewById<TextView>(R.id.capital_final_2)
        val rendimiento_2 = findViewById<TextView>(R.id.rendimiento_2)

        val home = findViewById<Button>(R.id.home)
        home.setOnClickListener{ cambiarVista(this, MainActivity()) }

        val CALCULAR_CONTEXT = getSharedPreferences("CALCULAR_CONTEXT", Context.MODE_PRIVATE)

        val MONTO_1 = CALCULAR_CONTEXT.getInt("MONTO_1", 0)
        val TASA_DE_INTERES_1 = CALCULAR_CONTEXT.getInt("TASA_DE_INTERES_1", 0)
        val PLAZO_1 = CALCULAR_CONTEXT.getInt("PLAZO_1", 0)
        val ENTIDAD_1 = CALCULAR_CONTEXT.getString("ENTIDAD_1", "")
        val TIPO_DE_INVERSION_1 = CALCULAR_CONTEXT.getString("TIPO_DE_INVERSION_1", "")

        val TASA_DIARIA_1 = ((TASA_DE_INTERES_1.toFloat() / 360) / 100)
        val CAPITAL_INICIAL_1 = MONTO_1.toFloat()
        val RENDIMIENTO_1 = "%.2f".format(MONTO_1 * TASA_DIARIA_1 * PLAZO_1)
        val CAPITAL_FINAL_1 = CAPITAL_INICIAL_1 + RENDIMIENTO_1.toFloat()
        val ROI_1 = "%.2f".format((CAPITAL_FINAL_1 - CAPITAL_INICIAL_1) / CAPITAL_INICIAL_1 * 100)

        entidad_1.text = ENTIDAD_1
        tipo_inversion_1.text = TIPO_DE_INVERSION_1
        tna_1.text = "TNA: ${TASA_DE_INTERES_1}%"
        capital_inicial_1.text = "Capital inicial: $$CAPITAL_INICIAL_1"
        capital_final_1.text = "Capital final: $$CAPITAL_FINAL_1"
        rendimiento_1.text = "Rendimiento: $$RENDIMIENTO_1 ($ROI_1%)"

        val MONTO_2 = CALCULAR_CONTEXT.getInt("MONTO_2", 0)
        val TASA_DE_INTERES_2 = CALCULAR_CONTEXT.getInt("TASA_DE_INTERES_2", 0)
        val PLAZO_2 = CALCULAR_CONTEXT.getInt("PLAZO_2", 0)
        val ENTIDAD_2 = CALCULAR_CONTEXT.getString("ENTIDAD_2", "")
        val TIPO_DE_INVERSION_2 = CALCULAR_CONTEXT.getString("TIPO_DE_INVERSION_2", "")

        val TASA_DIARIA_2 = ((TASA_DE_INTERES_2.toFloat() / 360) / 100)
        val CAPITAL_INICIAL_2 = MONTO_2.toFloat()
        val RENDIMIENTO_2 = "%.2f".format(MONTO_2 * TASA_DIARIA_2 * PLAZO_2)
        val CAPITAL_FINAL_2 = CAPITAL_INICIAL_2 + RENDIMIENTO_2.toFloat()
        val ROI_2 = "%.2f".format((CAPITAL_FINAL_2 - CAPITAL_INICIAL_2) / CAPITAL_INICIAL_2 * 100)

        entidad_2.text = ENTIDAD_2
        tipo_inversion_2.text = TIPO_DE_INVERSION_2
        tna_2.text = "TNA: ${TASA_DE_INTERES_2}%"
        capital_inicial_2.text = "Capital inicial: $$CAPITAL_INICIAL_2"
        capital_final_2.text = "Capital final: $$CAPITAL_FINAL_2"
        rendimiento_2.text = "Rendimiento: $$RENDIMIENTO_2 ($ROI_2%)"


        val HISTORIAL_CONTEXT = getSharedPreferences("HISTORIAL_CONTEXT", Context.MODE_PRIVATE)
    }
    private fun cambiarVista(context: Context, activity: AppCompatActivity){
        val intent = Intent(context, activity::class.java )
        startActivity(intent)
        finish()
    }
}