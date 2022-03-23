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

    private val delta = 0.0001

    @Test
    fun testeMaiorValor() {
        console.propoe(Lance(Usuario("Rachel"), 200.0))

        val maiorValorDevolvidoConsole = console.maiorLance

        assertEquals(200.0, maiorValorDevolvidoConsole, delta)

    }

    @Test
    fun testeMaiorLanceOrdemCrescente() {
        console.propoe(Lance(user1, 100.0))
        console.propoe(Lance(Usuario("Teste2"), 200.0))

        val maiorValorDevolvido = console.maiorLance

        assertEquals(200.0, maiorValorDevolvido, delta)
    }

    @Test
    fun testeMaiorLanceOrdemDecrescente() {
        console.propoe(Lance(user1, 200.0))
        console.propoe(Lance(Usuario("Teste2"), 100.0))

        val maiorValorDevolvidoCarro = console.maiorLance

        assertEquals(200.0, maiorValorDevolvidoCarro, delta)
    }

    @Test
    fun testeMenorValor() {
        console.propoe(Lance(user1, 200.0))

        val menorValorDevolvidoConsole = console.menorLance

        assertEquals(200.0, menorValorDevolvidoConsole, delta)

    }

    @Test
    fun testeMenorLanceOrdemCrescente() {
        console.propoe(Lance(user1, 100.0))
        console.propoe(Lance(Usuario("Teste2"), 200.0))

        val menorValorDevolvido = console.menorLance

        assertEquals(100.0, menorValorDevolvido, delta)
    }

    @Test
    fun testeMenorLanceOrdemDecrescente() {
        console.propoe(Lance(user1, 200.0))
        console.propoe(Lance(Usuario("Teste2"), 100.0))

        val menorValorDevolvidoCarro = console.menorLance

        assertEquals(100.0, menorValorDevolvidoCarro, delta)
    }

    @Test
    fun testeTresMaioresLances(){
        console.propoe(Lance(user1, 200.0))
        console.propoe(Lance(Usuario("Teste2"), 300.0))
        console.propoe(Lance(Usuario("Teste1"), 500.0))

        val listLances : MutableList<Lance> = console.getTresMaioresLances()

        assertEquals(3, listLances.size)
        assertEquals(500.0, listLances[0].valor, delta)
        assertEquals(300.0, listLances[1].valor, delta)
        assertEquals(200.0, listLances[2].valor, delta)
    }

    @Test
    fun tresMaioresLancesSemLance(){
        val listLances : MutableList<Lance> = console.getTresMaioresLances()

        assertEquals(0, listLances.size)
    }

    @Test
    fun treMaioresLancesComUmLance(){
        console.propoe(Lance(user1, 200.0))

        val listLances : MutableList<Lance> = console.getTresMaioresLances()

        assertEquals(1, listLances.size)
        assertEquals(200.0, listLances[0].valor, delta)
    }

    @Test
    fun treMaioresLancesComDoisLances(){
        console.propoe(Lance(user1, 200.0))
        console.propoe(Lance(Usuario("Teste2"), 400.0))

        val listLances : MutableList<Lance> = console.getTresMaioresLances()

        assertEquals(2, listLances.size)
        assertEquals(400.0, listLances[0].valor, delta)
        assertEquals(200.0, listLances[1].valor, delta)
    }

    @Test
    fun treMaioresLancesComMaisDeTresLances(){
        console.propoe(Lance(user1, 200.0))
        console.propoe(Lance(user1, 500.0))
        console.propoe(Lance(user1, 600.0))
        console.propoe(Lance(user1, 700.0))
        console.propoe(Lance(Usuario("Teste2"), 400.0))

        val listLances : MutableList<Lance> = console.getTresMaioresLances()

        assertEquals(3, listLances.size)
        assertEquals(700.0, listLances[0].valor, delta)
        assertEquals(600.0, listLances[1].valor, delta)
        assertEquals(500.0, listLances[2].valor, delta)
    }
}