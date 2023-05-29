package com.example.controladmin

import android.content.Context
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@Composable
fun ProfileScreen(personalAdmin: PersonalAdmin, context: Context) {
    val scrollState = rememberScrollState()
    Column(modifier = Modifier.fillMaxSize()) {
        BoxWithConstraints {
            Surface {
                Column(modifier = Modifier.fillMaxSize().verticalScroll(scrollState)) {
                    ProfileHeader(
                        personalAdmin = personalAdmin,
                        containerHeight = this@BoxWithConstraints.maxHeight,
                        personalAdmin.foto)
                    Nombre(personalAdmin = personalAdmin)
                    ProfileProperty(label = stringResource(id = com.example.controladmin.R.string.nombre), value = personalAdmin.nombre)
                    ProfileProperty(label = stringResource(id = com.example.controladmin.R.string.apellidos), value = personalAdmin.apellidos)
                    ProfileProperty(label = stringResource(id = com.example.controladmin.R.string.DNI), value = personalAdmin.DNI)
                    ProfileProperty(label = stringResource(id = com.example.controladmin.R.string.cargo), value = personalAdmin.cargo)
                    ProfileProperty(label = stringResource(id = com.example.controladmin.R.string.edad), value = personalAdmin.edad.toString())
                    ProfileProperty(label = stringResource(id = com.example.controladmin.R.string.telefono), value = personalAdmin.telefono)
                    Btn_Llamar(title = "Llamar al perfil", context,personalAdmin.telefono)
                    Btn_WhA(title = "WhatsApp al perfil", context,personalAdmin.telefono)
                    Spacer(Modifier.height((this@BoxWithConstraints.maxHeight-320.dp).coerceAtLeast(0.dp)))
                }
            }
        }
    }
}


@Composable
private fun ProfileHeader(personalAdmin: PersonalAdmin, containerHeight: Dp, foto:Int){
    AsyncImage(model = foto, contentDescription = null, contentScale = ContentScale.Crop,modifier = Modifier.heightIn(max=containerHeight/2).fillMaxWidth())
}

@Composable
private fun Nombre(personalAdmin: PersonalAdmin){
    Column(modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 16.dp, top = 16.dp)) {
        Text(text = personalAdmin.nombre, style= MaterialTheme.typography.h5, fontWeight = FontWeight.Bold)
    }
}

@Composable
fun ProfileProperty(label:String,value:String){
    Column(modifier= Modifier.padding(start = 16.dp, end = 16.dp, bottom = 16.dp)) {
        Divider(
            modifier = Modifier.padding(bottom = 4.dp))
        Text(
            text = label, modifier = Modifier.height(24.dp), style = MaterialTheme.typography.caption)
        Text(
            text = value, modifier = Modifier.height(24.dp), style = MaterialTheme.typography.body1, overflow = TextOverflow.Visible
        )
    }
}

@Composable
fun Btn_Llamar(title: String, context: Context,numero:String){
    Button(onClick = {
        Operaciones.llamarContacto(context,numero)
    },
        modifier = Modifier.fillMaxWidth().padding(10.dp)
    ) {
        Text(title)
    }
}

@Composable
fun Btn_WhA(title: String, context: Context,numero:String){
    Button(onClick = {
        Operaciones.onWhatsApp(context,numero)
    },
        modifier = Modifier.fillMaxWidth().padding(10.dp)
    ) {
        Text(title)
    }
}