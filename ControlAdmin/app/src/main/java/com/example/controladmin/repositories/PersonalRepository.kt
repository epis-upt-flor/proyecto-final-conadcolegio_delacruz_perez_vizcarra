package com.example.controladmin.repositories

import com.example.controladmin.model.Personal
import com.google.firebase.firestore.CollectionReference
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PersonalRepository
@Inject
constructor(
    private val personalList: CollectionReference
)
{ //este repositorio lo inyectaremos en el ViewModel (Hilt o inyeccion de dependencias)
    fun addNewPersonal(personal: Personal){
        try {
            personalList.document(personal.dni).set(personal)
        } catch (e: Exception){
            e.printStackTrace()
        }
    }
}