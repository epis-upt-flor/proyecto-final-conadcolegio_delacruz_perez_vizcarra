package com.example.controladmin.model

data class Personal(
    val id_personal: String,
    val nombre: String,
    val apellidos: String,
    val dni: String,
    val cargo: String,
    val telefono: String,
    val foto: String
){
    constructor() : this("","","","","","","")
}
