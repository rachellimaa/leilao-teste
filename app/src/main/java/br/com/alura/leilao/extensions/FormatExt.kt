package br.com.alura.leilao.extensions

import java.text.NumberFormat
import java.util.*

fun formatarReal(d: Double) : String {
    val ptBr = Locale("pt", "BR")
    return NumberFormat.getCurrencyInstance(ptBr).format(d)
}