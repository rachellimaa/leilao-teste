package br.com.alura.leilao.ui.activity

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import br.com.alura.leilao.R
import br.com.alura.leilao.model.Leilao

class LancesLeilaoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lances_leilao)
        val dadosRecebidos = intent
        if (dadosRecebidos.hasExtra("leilao")) {
            val leilao = dadosRecebidos.getSerializableExtra("leilao") as Leilao?
            val descricao = findViewById<TextView>(R.id.lances_leilao_descricao)
            val maiorLance = findViewById<TextView>(R.id.lances_leilao_maior_lance)
            val menorLance = findViewById<TextView>(R.id.lances_leilao_menor_lance)
            descricao.text = leilao!!.descricao
            maiorLance.text = leilao.maiorLance.toString()
            menorLance.text = leilao.menorLance.toString()

        }
    }
}