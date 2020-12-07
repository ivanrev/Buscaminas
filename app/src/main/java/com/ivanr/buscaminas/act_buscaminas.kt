package com.ivanr.buscaminas

import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class act_buscaminas : AppCompatActivity(), View.OnClickListener  {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_layout_dinamico)

        val v_bundle = intent.extras
        var v_x : Int = v_bundle!!.getInt("p_x")
        var v_y: Int = v_bundle!!.getInt("p_y")
        var v_minas: Int = v_bundle!!.getInt("p_numero_minas")

        var v_tablero: TableLayout = this.findViewById(R.id.lay_tablero)
        val v_lp = TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT)
        val v_lpb = TableRow.LayoutParams ()

        contruye_layout_tablero(v_tablero, v_lp, v_lpb, v_x, v_y)
        construye_array_tablero(v_x, v_y, v_minas)
    }
    override fun onClick(v: View) {
        var v_id_boton: Int = v.id
        Toast.makeText(this, "Mensaje del boton:"+v_id_boton.toString(), Toast.LENGTH_SHORT).show()
    }

    private fun contruye_layout_tablero (p_tablero: TableLayout, p_lp: TableRow.LayoutParams, p_lpb:TableRow.LayoutParams, p_x:Int, p_y:Int) {
        var v_Cont: Int = 0
        var v_dim: Int = 15
        when (p_x) {
            10 -> v_dim = 20
            20 -> v_dim = 8
            30 -> v_dim = 4
        }
        p_lpb.weight = v_dim.toFloat()
        p_lpb.width = v_dim
        p_lpb.bottomMargin = 1
        p_lpb.topMargin = 1
        p_lpb.marginEnd = 1
        p_lpb.marginStart = 1

        //Filas
        for (c_y in 0 until p_y) {
            val fila = TableRow(this)
            fila.layoutParams = p_lp
            //Columnas
            for (c_x in 0 until p_x) {
                val bt = Button(this)
                bt.layoutParams = p_lpb
                bt.id = v_Cont
                bt.setOnClickListener(this)
                fila.addView(bt)
                v_Cont++
            }
            p_tablero.addView(fila)
        }
    }

    private fun existeContador (p_contador: Int, p_ar_aleatorios: IntArray): Boolean {
        var v_ret: Boolean = false
        for (i: Int in 0..p_ar_aleatorios.size-1) {
            if (p_ar_aleatorios[i]==p_contador){
                v_ret = true
            }
        }
        return v_ret
    }

    private fun construye_array_tablero (p_x: Int, p_y: Int, p_minas: Int) {
        var matriz_tablero = arrayOf<Array<Int>>()
        var ar_minas = IntArray(p_minas, { 0 } )
        var v_total: Int = (p_x * p_y)-1
        for (i:Int in 0..p_minas-1){
            ar_minas[i] = (0..v_total).random()
        }

        //Inicializa Matriz
        for (i in 0..p_y) {
            var array = arrayOf<Int>()
            for (j in 0..p_x) {
                array+=0
            }
            matriz_tablero += array
        }

        //Colocar minas aleatorias
        var v_Cont: Int = 0
        for (i:Int in 0..p_y) {
            for (j: Int in 0..p_x) {
                if (existeContador(v_Cont, ar_minas)) {
                    matriz_tablero[i][j] = 1
                }
                v_Cont++
            }
        }
        v_Cont = 0
    }
}