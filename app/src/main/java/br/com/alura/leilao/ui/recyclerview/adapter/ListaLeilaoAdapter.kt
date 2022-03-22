package br.com.alura.leilao.ui.recyclerview.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.alura.leilao.R
import br.com.alura.leilao.model.Leilao

class ListaLeilaoAdapter(private val context: Context, private val leiloes: List<Leilao>) :
    RecyclerView.Adapter<ListaLeilaoAdapter.ViewHolder>() {

    var onItemClick: ((Leilao?) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewCriada = LayoutInflater.from(context).inflate(R.layout.item_leilao, parent, false)
        return ViewHolder(viewCriada)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val leilao = pegaLeilaoPorPosicao(position)
        holder.vincula(leilao)
    }

    override fun getItemCount(): Int {
        return leiloes.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val descricao: TextView = itemView.findViewById(R.id.item_leilao_descricao)
        private val maiorLance: TextView = itemView.findViewById(R.id.item_leilao_maior_lance)
        private var leilao: Leilao? = null
        fun vincula(leilao: Leilao) {
            this.leilao = leilao
            descricao.text = leilao.descricao
            maiorLance.text = leilao.maiorLance.toString()
        }

        init {
            itemView.setOnClickListener { onItemClick?.invoke(leilao) }
        }
    }

    fun pegaLeilaoPorPosicao(posicao: Int): Leilao {
        return leiloes[posicao]
    }
}