package com.example.controladmin.repositories

import com.example.controladmin.model.Personal
import com.google.firebase.firestore.CollectionReference
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
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

    fun getPersonalList(): Flow<Result<List<Personal>>> = flow{
        try {
            emit(Result.Loading<List<Personal>>())
            val personalList = personalList.get().await().map(){document ->
                document.toObject(Personal::class.java)
            }

            emit(Result.Success<List<Personal>>(data = personalList))
        } catch(e: Exception){
            emit(Result.Error<List<Personal>>(message = e.localizedMessage ?: "Error Desconocido"))
        }
    }
}