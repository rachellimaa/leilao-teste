package br.com.alura.leilao.model

import java.io.Serializable

class Lance(val usuario: Usuario, val valor: Double) : Serializable{
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Lance

        if (usuario != other.usuario) return false
        if (valor != other.valor) return false

        return true
    }

    override fun hashCode(): Int {
        var result = usuario.hashCode()
        result = 31 * result + valor.hashCode()
        return result
    }

}