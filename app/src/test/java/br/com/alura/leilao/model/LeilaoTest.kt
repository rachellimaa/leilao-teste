package br.com.alura.leilao.model

import br.com.alura.leilao.exception.LanceMenorQueUltimoLanceException
import br.com.alura.leilao.exception.LanceSeguidoDoMesmoUsuarioException
import br.com.alura.leilao.exception.UsuarioJaFezCincoLancesException
import org.junit.Assert.*
import org.junit.Test

class LeilaoTest {

    private val console = Leilao("Console")
    private val user1 = Usuario("Teste1")

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
    fun testeTresMaioresLances() {
        console.propoe(Lance(user1, 200.0))
        console.propoe(Lance(Usuario("Teste2"), 300.0))
        console.propoe(Lance(Usuario("Teste1"), 500.0))

        val listLances: MutableList<Lance> = console.getTresMaioresLances()

        assertEquals(3, listLances.size)
        assertEquals(500.0, listLances[0].valor, delta)
        assertEquals(300.0, listLances[1].valor, delta)
        assertEquals(200.0, listLances[2].valor, delta)
    }

    @Test
    fun tresMaioresLancesSemLance() {
        val listLances: MutableList<Lance> = console.getTresMaioresLances()

        assertEquals(0, listLances.size)
    }

    @Test
    fun treMaioresLancesComUmLance() {
        console.propoe(Lance(user1, 200.0))

        val listLances: MutableList<Lance> = console.getTresMaioresLances()

        assertEquals(1, listLances.size)
        assertEquals(200.0, listLances[0].valor, delta)
    }

    @Test
    fun treMaioresLancesComDoisLances() {
        console.propoe(Lance(user1, 200.0))
        console.propoe(Lance(Usuario("Teste2"), 400.0))

        val listLances: MutableList<Lance> = console.getTresMaioresLances()

        assertEquals(2, listLances.size)
        assertEquals(400.0, listLances[0].valor, delta)
        assertEquals(200.0, listLances[1].valor, delta)
    }

    @Test
    fun treMaioresLancesComMaisDeTresLances() {
        console.propoe(Lance(user1, 100.0))
        console.propoe(Lance(Usuario("Teste2"), 200.0))
        console.propoe(Lance(user1, 300.0))
        console.propoe(Lance(Usuario("Teste2"), 400.0))
        console.propoe(Lance(user1, 500.0))
        console.propoe(Lance(Usuario("Teste2"), 600.0))

        val listLances: MutableList<Lance> = console.getTresMaioresLances()

        assertEquals(3, listLances.size)
        assertEquals(600.0, listLances[0].valor, delta)
        assertEquals(500.0, listLances[1].valor, delta)
        assertEquals(400.0, listLances[2].valor, delta)
    }

    @Test
    fun maiorLanceDevolverZeroQuandoNaoTiverLance() {
        val maiorLanceDevolvido = console.maiorLance

        assertEquals(0.0, maiorLanceDevolvido, delta)
    }

    @Test
    fun menorLanceDevolverZeroQuandoNaoTiverLance() {
        val menorLanceDevolvido = console.menorLance

        assertEquals(0.0, menorLanceDevolvido, delta)
    }

    @Test(expected = LanceMenorQueUltimoLanceException::class)
    fun adicionarLanceQuandoForMenorQueOMaiorLance() {
        console.propoe(Lance(user1, 700.0))
        console.propoe(Lance(Usuario("Teste2"), 400.0))
    }

    @Test(expected = LanceSeguidoDoMesmoUsuarioException::class)
    fun adicionarLanceQuandoForOMesmoUsuario() {
        console.propoe(Lance(user1, 500.0))
        console.propoe(Lance(Usuario("Teste1"), 600.0))
    }

    @Test(expected = UsuarioJaFezCincoLancesException::class)
    fun adicionarLanceQuandoOUsuarioDerCincoLances() {
        val user2 = Usuario("Teste2")
        console.propoe(Lance(user1, 100.0))
        console.propoe(Lance(user2, 200.0))
        console.propoe(Lance(user1, 300.0))
        console.propoe(Lance(user2, 400.0))
        console.propoe(Lance(user1, 500.0))
        console.propoe(Lance(user2, 600.0))
        console.propoe(Lance(user1, 700.0))
        console.propoe(Lance(user2, 800.0))
        console.propoe(Lance(user1, 900.0))
        console.propoe(Lance(user2, 1000.0))
        console.propoe(Lance(user1, 1100.0))
    }

    @Test
    fun testJunit(){
        assertEquals(4, 2 + 2)
        assertNotEquals(3, 2 + 2)

        assertTrue(true)
        assertFalse(false)

        assertNull(null)
        assertNotNull(Usuario("Rachel"))

    }
}