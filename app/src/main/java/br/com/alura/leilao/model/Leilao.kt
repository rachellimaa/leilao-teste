package br.com.alura.leilao.model

import java.io.Serializable
import java.util.*
import kotlin.collections.ArrayList

class Leilao(val descricao: String) : Serializable {
    var lances: MutableList<Lance> = mutableListOf()
    var maiorLance: Double = Double.NEGATIVE_INFINITY
    var menorLance: Double = Double.POSITIVE_INFINITY

    fun propoe(lance: Lance) {
        lances.add(lance)
        lances.sortByDescending { it.valor }
        val valorLance = lance.valor
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
}