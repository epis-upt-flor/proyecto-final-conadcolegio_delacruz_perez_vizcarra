package com.example.controladmin

import java.io.Serializable

class PersonalAdmin(
    val ID_PersonalAdmin: Int,
    val nombre: String,
    val apellidos: String,
    val DNI: String,
    val cargo: String,
    val edad: Int,
    val telefono: String,
    val foto: Int
    ): Serializable