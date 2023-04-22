package com.example.controladmin

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@Composable
fun StaffListItem(personalAdmin: PersonalAdmin, navigateToProfile: (PersonalAdmin) -> Unit){
    Card(modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp, vertical = 8.dp), shape= RoundedCornerShape(corner = CornerSize(16.dp))) {
        Row(Modifier.clickable { navigateToProfile(personalAdmin) }){
            StaffImage(personalAdmin)
            Column(modifier = Modifier.padding(16.dp).fillMaxWidth().align(Alignment.CenterVertically)) {
                Text(text = personalAdmin.nombre, style = MaterialTheme.typography.h6)
                Text(text = "REVISAR PERFIL", style= MaterialTheme.typography.caption)
            }
        }
    }
}


@Composable
fun StaffImage(personalAdmin: PersonalAdmin){
    AsyncImage(model = personalAdmin.foto,contentDescription = personalAdmin.nombre,contentScale = ContentScale.Crop, modifier = Modifier.padding(8.dp).size(84.dp).clip(RoundedCornerShape(corner = CornerSize(16.dp))))
}