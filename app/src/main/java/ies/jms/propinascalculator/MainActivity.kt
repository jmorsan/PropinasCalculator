package ies.jms.propinascalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import ies.jms.propinascalculator.databinding.ActivityMainBinding
import kotlin.math.ceil

class MainActivity : AppCompatActivity() {

    lateinit var bindig: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindig = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bindig.root)

        val botonCalcular : Button = bindig.botonCalculo
        botonCalcular.setOnClickListener(){
            calcularPropina()
        }
    }

    fun calcularPropina (){
        val costeServicio = bindig.importeText.text.toString().toDoubleOrNull()
        if(costeServicio == null){
            Toast.makeText(this, "Introduce un importe correcto", Toast.LENGTH_SHORT).show()
            bindig.propina.text = ""
            return
        }



        val opcionSeleccionada = bindig.opciones.checkedRadioButtonId
        val porcentajeAplicar = when (opcionSeleccionada){
            R.id.opcion1 -> 0.2
            R.id.opcion2 -> 0.1
            else -> 0.05
        }
        var propina = costeServicio*porcentajeAplicar
        val redondeo = bindig.swichRedondeo.isChecked
        if(redondeo)
            propina = ceil(propina)

        bindig.propina.text = propina.toString()
    }
}