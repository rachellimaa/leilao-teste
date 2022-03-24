package br.com.alura.leilao.ui.activity

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import br.com.alura.leilao.R
import br.com.alura.leilao.extensions.formatarReal
import br.com.alura.leilao.model.Lance
import br.com.alura.leilao.model.Leilao
import java.lang.StringBuilder
import java.text.NumberFormat
import java.util.*

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
            val maioresLances = findViewById<TextView>(R.id.lances_leilao_maiores_lances)
            descricao.text = leilao!!.descricao
            maiorLance.text = formatarReal(leilao.maiorLance)
            menorLance.text = formatarReal(leilao.menorLance)
            val maioresLancesResult = StringBuilder()
            leilao.getTresMaioresLances().forEach {
                maioresLancesResult.append("${formatarReal(it.valor)} \n")
            }
            maioresLances.text = maioresLancesResult
        }
    }
}