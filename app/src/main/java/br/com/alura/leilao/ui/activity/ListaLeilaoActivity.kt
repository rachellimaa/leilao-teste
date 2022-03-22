package br.com.alura.leilao.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import br.com.alura.leilao.R
import br.com.alura.leilao.model.Lance
import br.com.alura.leilao.model.Leilao
import br.com.alura.leilao.model.Usuario
import br.com.alura.leilao.ui.recyclerview.adapter.ListaLeilaoAdapter
import java.util.*

class ListaLeilaoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_leilao)
        val adapter = ListaLeilaoAdapter(this, leiloesDeExemplo())
        val recyclerView = findViewById<RecyclerView>(R.id.lista_leilao_recyclerview)
        recyclerView.adapter = adapter
        adapter.onItemClick = { leilao ->
            val vaiParaLancesLeilao =
                Intent(this@ListaLeilaoActivity, LancesLeilaoActivity::class.java)
            vaiParaLancesLeilao.putExtra("leilao", leilao)
            startActivity(vaiParaLancesLeilao)
        }
    }
}

private fun leiloesDeExemplo(): List<Leilao> {
    val console = Leilao("Console")
    console.propoe(Lance(Usuario("Alex"), 300.0))
    console.propoe(Lance(Usuario("Rachel Lima"), 200.0))
    return ArrayList(
        listOf(
            console
        )
    )
}