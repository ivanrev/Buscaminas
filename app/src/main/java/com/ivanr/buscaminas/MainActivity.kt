package com.ivanr.buscaminas

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton


class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var btn10X10 : MaterialButton
    private lateinit var btn20X20 : MaterialButton
    private lateinit var btn30X30 : MaterialButton
    private lateinit var tv_numeroMinas : EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        instancias()
        acciones()
    }

    private fun instancias () {
        btn10X10 = findViewById(R.id.bt_10x10)
        btn20X20 = findViewById(R.id.bt_20x20)
        btn30X30 = findViewById(R.id.bt_30x30)
        tv_numeroMinas = findViewById(R.id.tv_NumeroMinas)

    }
    private fun acciones () {
        btn10X10.setOnClickListener(this)
        btn20X20.setOnClickListener(this)
        btn30X30.setOnClickListener(this)
    }
    private fun generaLayout(p_x: Int, p_y: Int) {

        val i = Intent(this, act_buscaminas::class.java)
        i.putExtra ("p_x",p_x)
        i.putExtra("p_y", p_y)
        i.putExtra("p_numero_minas",Integer.parseInt(tv_numeroMinas.text.toString()))
        startActivity(i)
    }

    override fun onClick(v: View) {
        when(v.id) {
            R.id.bt_10x10 -> generaLayout(10, 10)
            R.id.bt_20x20 -> generaLayout(20, 20)
            R.id.bt_30x30 -> generaLayout(30, 30)
        }
    }
}
