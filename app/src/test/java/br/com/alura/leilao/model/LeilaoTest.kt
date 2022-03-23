package br.com.alura.leilao.model

import org.junit.Assert.assertEquals
import org.junit.Test

class LeilaoTest {

    val console = Leilao("Console")
    val user1 = Usuario("Teste1")

    @Test
    fun testeDescricao() {
        val descricaoDevolvida = console.descricao
        assertEquals("Console", descricaoDevolvida)
    }

    @Test
    fun testeMaiorValor() {
        console.propoe(Lance(Usuario("Rachel"), 200.0))

        val maiorValorDevolvidoConsole = console.maiorLance

        assertEquals(200.0, maiorValorDevolvidoConsole, 0.0001)

    }

    @Test
    fun testeMaiorLanceOrdemCrescente() {
        console.propoe(Lance(user1, 100.0))
        console.propoe(Lance(Usuario("Teste2"), 200.0))

        val maiorValorDevolvido = console.maiorLance

        assertEquals(200.0, maiorValorDevolvido, 0.0001)
    }

    @Test
    fun testeMaiorLanceOrdemDecrescente() {
        console.propoe(Lance(user1, 200.0))
        console.propoe(Lance(Usuario("Teste2"), 100.0))

        val maiorValorDevolvidoCarro = console.maiorLance

        assertEquals(200.0, maiorValorDevolvidoCarro, 0.0001)
    }

    @Test
    fun testeMenorValor() {
        console.propoe(Lance(user1, 200.0))

        val menorValorDevolvidoConsole = console.menorLance

        assertEquals(200.0, menorValorDevolvidoConsole, 0.0001)

    }

    @Test
    fun testeMenorLanceOrdemCrescente() {
        console.propoe(Lance(user1, 100.0))
        console.propoe(Lance(Usuario("Teste2"), 200.0))

        val menorValorDevolvido = console.menorLance

        assertEquals(100.0, menorValorDevolvido, 0.0001)
    }

    @Test
    fun testeMenorLanceOrdemDecrescente() {
        console.propoe(Lance(user1, 200.0))
        console.propoe(Lance(Usuario("Teste2"), 100.0))

        val menorValorDevolvidoCarro = console.menorLance

        assertEquals(100.0, menorValorDevolvidoCarro, 0.0001)
    }
}