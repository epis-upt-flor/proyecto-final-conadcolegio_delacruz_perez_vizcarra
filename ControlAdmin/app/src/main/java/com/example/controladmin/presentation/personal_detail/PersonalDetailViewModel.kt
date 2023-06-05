package com.example.controladmin.presentation.personal_detail

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.controladmin.model.Personal
import com.example.controladmin.repositories.PersonalRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class PersonalDetailViewModel
@Inject
constructor(
    private val personalRepository: PersonalRepository
): ViewModel() {
    private val _state: MutableState<PersonalDetailState> = mutableStateOf(PersonalDetailState())
    val state: State<PersonalDetailState>
        get() = _state

    fun addNewPersonal(nombre: String, apellidos: String, dni: String, cargo: String, telefono: String){
        val personal = Personal(
            id_personal = UUID.randomUUID().toString(),
            nombre = nombre,
            apellidos = apellidos,
            dni = dni,
            cargo = cargo,
            telefono = telefono,
            foto = ""
        )

        personalRepository.addNewPersonal(personal)
    }
}