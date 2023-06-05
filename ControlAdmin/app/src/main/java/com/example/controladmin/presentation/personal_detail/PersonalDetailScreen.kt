package com.example.controladmin.presentation.personal_detail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.controladmin.ui.theme.Purple700

@Composable
fun PersonalDetailScreen(
    state: PersonalDetailState,
    addNewPersonal: (String, String, String, String, String) -> Unit
) {
    var nombre by remember { mutableStateOf("") }
    var apellidos by remember { mutableStateOf("") }
    var dni by remember { mutableStateOf("") }
    var cargo by remember { mutableStateOf("") }
    var telefono by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ){
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp),
                value = nombre,
                onValueChange = { nombre = it},
                label = {
                    Text(text = "Nombre")
                }
            )

            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp),
                value = apellidos,
                onValueChange = { apellidos = it},
                label = {
                    Text(text = "Apellidos")
                }
            )

            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp),
                value = dni,
                onValueChange = { dni = it},
                label = {
                    Text(text = "DNI")
                }
            )

            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp),
                value = cargo,
                onValueChange = { cargo = it},
                label = {
                    Text(text = "Cargo")
                }
            )

            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp),
                value = telefono,
                onValueChange = { telefono = it},
                label = {
                    Text(text = "Telefono")
                }
            )
        }

        if (state.error.isNotBlank()){
            Text(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp),
                text = state.error,
                style = TextStyle(
                    color = Color.Red,
                    textAlign = TextAlign.Center
                )
            )
        }

        if(state.isLoading){
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        } else {
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter),
                onClick = {
                    addNewPersonal(nombre,apellidos,dni,cargo,telefono)
                },
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Purple700
                )
            ) {
                Text(
                    text = "Anadir Personal",
                    color = Color.White
                )
            }
        }
    }
}