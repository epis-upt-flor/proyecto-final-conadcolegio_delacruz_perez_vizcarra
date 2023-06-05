package com.example.controladmin.presentation.profile

import android.content.Context
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.controladmin.Operaciones
import com.example.controladmin.PersonalAdmin
import com.example.controladmin.R
import com.example.controladmin.presentation.sign_in.UserData

@Composable
fun ProfileScreen(userData: UserData?, onSignOut: () -> Unit, checkPersonal: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if(userData?.profilePictureUrl != null) {
            AsyncImage(
                model = userData.profilePictureUrl,
                contentDescription = "Profile picture",
                modifier = Modifier
                    .size(150.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(16.dp))
        }
        if(userData?.username != null) {
            Text(
                text = userData.username,
                textAlign = TextAlign.Center,
                fontSize = 36.sp,
                fontWeight = FontWeight.SemiBold
            )
            Spacer(modifier = Modifier.height(16.dp))
        }
        Button(onClick = onSignOut) {
            Text(text = "Sign out")
        }
        Button(onClick = checkPersonal) {
            Text(text = "Ver Personal")
        }
    }

    //-----------------------------------
    /*
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
                    ProfileProperty(label = stringResource(id = R.string.nombre), value = personalAdmin.nombre)
                    ProfileProperty(label = stringResource(id = R.string.apellidos), value = personalAdmin.apellidos)
                    ProfileProperty(label = stringResource(id = R.string.DNI), value = personalAdmin.DNI)
                    ProfileProperty(label = stringResource(id = R.string.cargo), value = personalAdmin.cargo)
                    ProfileProperty(label = stringResource(id = R.string.edad), value = personalAdmin.edad.toString())
                    ProfileProperty(label = stringResource(id = R.string.telefono), value = personalAdmin.telefono)
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
        Operaciones.llamarContacto(context, numero)
    },
        modifier = Modifier.fillMaxWidth().padding(10.dp)
    ) {
        Text(title)
    }
}

@Composable
fun Btn_WhA(title: String, context: Context,numero:String){
    Button(onClick = {
        Operaciones.onWhatsApp(context, numero)
    },
        modifier = Modifier.fillMaxWidth().padding(10.dp)
    ) {
        Text(title)
    }*/
}