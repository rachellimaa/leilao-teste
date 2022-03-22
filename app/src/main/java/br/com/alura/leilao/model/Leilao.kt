package br.com.alura.leilao.model

import java.io.Serializable
import java.util.*
import kotlin.collections.ArrayList

class Leilao(val descricao: String) : Serializable {
    private val lances: List<Lance> = ArrayList()
    var maiorLance: Double = Double.NEGATIVE_INFINITY
    var menorLance: Double = Double.POSITIVE_INFINITY

    fun propoe(lance: Lance) {
        val valorLance = lance.valor
        if (valorLance > maiorLance){
            maiorLance = valorLance
        }
        if (valorLance < menorLance){
            menorLance = valorLance
        }
    }
}