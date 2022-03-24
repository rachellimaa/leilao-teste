package br.com.alura.leilao.model

import br.com.alura.leilao.exception.LanceMenorQueUltimoLanceException
import br.com.alura.leilao.exception.LanceSeguidoDoMesmoUsuarioException
import br.com.alura.leilao.exception.UsuarioJaFezCincoLancesException
import java.io.Serializable

class Leilao(val descricao: String) : Serializable {
    private var lances: MutableList<Lance> = mutableListOf()
    var maiorLance: Double = 0.0
    var menorLance: Double = 0.0

    fun propoe(lance: Lance) {
        val valorLance = lance.valor

        if (maiorLance > valorLance) return throw LanceMenorQueUltimoLanceException()

        if (lances.isNotEmpty()) {
            val usuarioNovo = lance.usuario
            val ultimoUsuario = lances[0].usuario

            if (usuarioNovo == ultimoUsuario) return throw LanceSeguidoDoMesmoUsuarioException()

            var lancesDoUsuario = 0
            lances.forEach {
                val usuarioExistente = it.usuario
                if (usuarioExistente == usuarioNovo) {
                    lancesDoUsuario++
                    if (lancesDoUsuario == 5) return throw UsuarioJaFezCincoLancesException()
                }
            }
        }

        lances.add(lance)
        lances.sortByDescending { it.valor }

        if (lances.size == 1) {
            maiorLance = valorLance
            menorLance = valorLance
        }

        calculaMaiorLance(valorLance)
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