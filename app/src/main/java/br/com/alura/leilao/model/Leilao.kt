package br.com.alura.leilao.model

import java.io.Serializable

class Leilao(val descricao: String) : Serializable {
    private var lances: MutableList<Lance> = mutableListOf()
    var maiorLance: Double = 0.0
    var menorLance: Double = 0.0

    fun propoe(lance: Lance) {
        val valorLance = lance.valor

        if (maiorLance > valorLance) return

        if (lances.isNotEmpty()){
            val usuarioNovo = lance.usuario
            val ultimoUsuario = lances[0].usuario

            if (usuarioNovo.equals(ultimoUsuario)) return
        }

        lances.add(lance)
        lances.sortByDescending { it.valor }

        if (lances.size == 1){
            maiorLance = valorLance
            menorLance = valorLance
        }

        calculaMaiorLance(valorLance)
        calculaMenorLance(valorLance)
    }

    private fun calculaMenorLance(valorLance: Double) {
        if (valorLance < menorLance) {
            menorLance = valorLance
        }
    }

    private fun calculaMaiorLance(valorLance: Double) {
        if (valorLance > maiorLance) {
            maiorLance = valorLance
        }
    }

    fun getTresMaioresLances(): MutableList<Lance> {
        var quantidade = lances.size
        if (quantidade > 3) quantidade = 3
        return lances.subList(0, quantidade)
    }

    fun quantidadeDevolvida(): Int = lances.size
}