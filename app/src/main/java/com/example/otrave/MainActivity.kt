package com.example.otrave

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    var texto1: TextView? = null

    var boton1: Button? = null
    var boton2: Button? = null
    var boton3: Button? = null
    var boton4: Button? = null
    var boton5: Button? = null
    var boton6: Button? = null
    var boton7: Button? = null
    var boton8: Button? = null
    var boton9: Button? = null
    var boton10: Button? = null
    var boton11: Button? = null
    var boton12: Button? = null

    var operacion = 0
    var numero1:Long = 0
    var numero2:Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        texto1 = findViewById(R.id.resultado)
        boton1 = findViewById(R.id.bitsiqz)
        boton2 = findViewById(R.id.bitsder)
        boton3 = findViewById(R.id.suma)
        boton4 = findViewById(R.id.uno)
        boton5 = findViewById(R.id.cero)
        boton6 = findViewById(R.id.restar)
        boton7 = findViewById(R.id.eliminar)
        boton8 = findViewById(R.id.signo)
        boton9 = findViewById(R.id.multiplicar)
        boton10 = findViewById(R.id.limpiar)
        boton11 = findViewById(R.id.igual)
        boton12 = findViewById(R.id.dividir)

        boton1?.setOnClickListener { desplazarIzq() }
        boton2?.setOnClickListener { desplazarDer() }
        boton3?.setOnClickListener { operacionPresionada(suma) }
        boton4?.setOnClickListener { numPresionado("1") }
        boton5?.setOnClickListener { numPresionado("0") }
        boton6?.setOnClickListener { operacionPresionada(resta) }
        boton7?.setOnClickListener { eliminar() }
        boton8?.setOnClickListener { operacionPresionada(negativo) }
        boton9?.setOnClickListener { operacionPresionada(multiplicacion) }
        boton10?.setOnClickListener { borrarTodo() }
        boton11?.setOnClickListener { igualar() }
        boton12?.setOnClickListener { operacionPresionada(division) }

    }

    private fun numPresionado(digito: String) {
        texto1?.text = "${texto1?.text}$digito"

        if (operacion == noOperacion) {
            numero1 = texto1?.text.toString().toLong(2)
        } else {
            numero2 = texto1?.text.toString().toLong(2)
        }
    }

    private fun borrarTodo() {
        numero1 = 0
        numero2 = 0
        texto1?.setText("")
        operacion = noOperacion
    }

    private fun igualar() {
        var resultado = when (operacion) {
            suma -> numero1 + numero2
            resta -> numero1 - numero2
            multiplicacion -> numero1 * numero2
            division -> if (numero2>=1) {
                numero1 / numero2
            } else {
                Log.i("mensaje", "nao pode")
            }
            negativo -> numero1*(-1)
            else -> 0
        }
        var numBin = resultado.toString().toInt()
        texto1?.text = Integer.toBinaryString(numBin)

    }

    private fun eliminar() {
        if (operacion == noOperacion) {
            var textoNum = ""
            textoNum = texto1?.text.toString()
            if (textoNum=="") {
                numero1 = 0
                texto1?.setText("")
            } else if (textoNum.length==1) {
                numero1 = 0
                texto1?.setText("")
            } else {
                textoNum = textoNum.substring(0, textoNum.length - 1)
                numero1 = textoNum.toLong()
                texto1?.setText(numero1.toString())
            }
        } else {
            var textoNum = ""
            textoNum = texto1?.text.toString()
            if (textoNum=="") {
                numero2= 0
                texto1?.setText("")
            } else if (textoNum.length==1) {
                numero2 = 0
                texto1?.setText("")
            } else {
                textoNum = textoNum.substring(0, textoNum.length - 1)
                numero2 = textoNum.toLong()
                texto1?.setText(numero2.toString())
            }
        }
    }

    private fun operacionPresionada(operacion: Int) {
        this.operacion = operacion
        texto1?.text = ""
    }


    private fun desplazarDer() {
        if (texto1?.text=="") {
            texto1?.text="0"
        }
        val numero = texto1?.text.toString().toInt(2)
        val numeroRotadoDerecha = numero shr 1
        texto1?.text = ""
        texto1?.text = (Integer.toBinaryString(numeroRotadoDerecha))
    }

    private fun desplazarIzq() {
        if (texto1?.text=="") {
            texto1?.text="0"
        }
        val numero = texto1?.text.toString().toInt(2)
        val numeroRotadoIzquierda = numero shl 1
        texto1?.text = ""
        texto1?.text = (Integer.toBinaryString(numeroRotadoIzquierda))
    }

    companion object {
        val suma = 1
        val resta = 2
        val multiplicacion = 3
        val division = 4
        val negativo = 5
        val noOperacion = 0
    }

    private fun negar() {
        if (operacion == noOperacion) {
            var negativo = numero1*(-1)
            var negativoNum = negativo.toInt()
            texto1?.text = Integer.toBinaryString(negativoNum)
            numero1 = texto1?.text.toString().toLong(2)
        } else {
            var negativo = numero2*(-1)
            var negativoNum = negativo.toInt()
            texto1?.text = Integer.toBinaryString(negativoNum)
            numero2 = texto1?.text.toString().toLong(2)
        }

    }

}