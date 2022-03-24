package br.com.alura.leilao.model

import br.com.alura.leilao.exception.LanceMenorQueUltimoLanceException
import br.com.alura.leilao.exception.LanceSeguidoDoMesmoUsuarioException
import br.com.alura.leilao.exception.UsuarioJaFezCincoLancesException
import br.com.alura.leilao.extensions.formatarReal
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.*
import org.junit.Assert.*
import org.junit.Test

class LeilaoTest {

    private val console = Leilao("Console")
    private val user1 = Usuario("Teste1")

    @Test
    fun testeDescricao() {
        val descricaoDevolvida = console.descricao

        assertThat(descricaoDevolvida, equalTo("Console"))
    }

    private val delta = 0.0001

    @Test
    fun testeMaiorValor() {
        console.propoe(Lance(Usuario("Rachel"), 200.0))

        val maiorValorDevolvidoConsole = console.maiorLance

        assertThat(maiorValorDevolvidoConsole, closeTo(200.0, delta))

    }

    @Test
    fun testeMaiorLanceOrdemCrescente() {
        console.propoe(Lance(user1, 100.0))
        console.propoe(Lance(Usuario("Teste2"), 200.0))

        val maiorValorDevolvido = console.maiorLance

        assertThat(maiorValorDevolvido, closeTo(200.0, delta))
    }

    @Test
    fun testeMenorValor() {
        console.propoe(Lance(user1, 200.0))

        val menorValorDevolvidoConsole = console.menorLance

        assertThat(menorValorDevolvidoConsole, closeTo(200.0, delta))
    }

    @Test
    fun testeMenorLanceOrdemCrescente() {
        console.propoe(Lance(user1, 100.0))
        console.propoe(Lance(Usuario("Teste2"), 200.0))

        val menorValorDevolvido = console.menorLance

        assertThat(menorValorDevolvido, closeTo(100.0, delta))
    }

    @Test
    fun testeTresMaioresLances() {
        console.propoe(Lance(user1, 200.0))
        console.propoe(Lance(Usuario("Teste2"), 300.0))
        console.propoe(Lance(Usuario("Teste1"), 500.0))

        val listLances: MutableList<Lance> = console.getTresMaioresLances()

   //     assertThat(listLances, hasSize(3))

        // assertThat(listLances, hasItem(Lance(Usuario("Teste1"), 500.0)))

//        assertThat(listLances, contains(
//            Lance(Usuario("Teste1"), 500.0),
//            Lance(Usuario("Teste2"), 300.0),
//            Lance(Usuario("Teste1"), 200.0)
//        ))

        assertThat(
            listLances,
            both(hasItem(Lance(Usuario("Teste1"), 500.0))).and(
                contains(
                    Lance(Usuario("Teste1"), 500.0),
                    Lance(Usuario("Teste2"), 300.0),
                    Lance(Usuario("Teste1"), 200.0)
                )
            )
        )

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

        assertThat(listLances, hasSize(1))
        assertEquals(200.0, listLances[0].valor, delta)
    }

    @Test
    fun treMaioresLancesComDoisLances() {
        console.propoe(Lance(user1, 200.0))
        console.propoe(Lance(Usuario("Teste2"), 400.0))

        val listLances: MutableList<Lance> = console.getTresMaioresLances()

        assertThat(listLances, hasSize(2))
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

        assertThat(listLances, hasSize(3))
        assertEquals(600.0, listLances[0].valor, delta)
        assertEquals(500.0, listLances[1].valor, delta)
        assertEquals(400.0, listLances[2].valor, delta)
    }

    @Test
    fun maiorLanceDevolverZeroQuandoNaoTiverLance() {
        val maiorLanceDevolvido = console.maiorLance

        assertThat(maiorLanceDevolvido, closeTo(0.0, delta))
    }

    @Test
    fun menorLanceDevolverZeroQuandoNaoTiverLance() {
        val menorLanceDevolvido = console.menorLance

        assertThat(menorLanceDevolvido, closeTo(0.0, delta))
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
    fun testJunit() {
        assertEquals(4, 2 + 2)
        assertNotEquals(3, 2 + 2)

        assertTrue(true)
        assertFalse(false)

        assertNull(null)
        assertNotNull(Usuario("Rachel"))

    }

    @Test
    fun adicionarFormatacao(){
        assertThat("R$ 200,00", equalTo(formatarReal(200.0)))
    }
}