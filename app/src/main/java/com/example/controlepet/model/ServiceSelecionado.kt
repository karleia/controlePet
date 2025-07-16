package com.example.controlepet.model

data class ServiceSelecionado(
    val idService: Int,
    val name: String,
    val price: Double
) {
    val labelComPreco: String
        get() = "$name - R$ ${"%.2f".format(price)}"
}
