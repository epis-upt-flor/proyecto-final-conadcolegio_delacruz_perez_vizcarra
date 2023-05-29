package com.example.controladmin

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ColegioRAStaff(navigateToProfile: (PersonalAdmin) -> Unit) {
    val direccion = remember {
        DataProvider.direccionList
    }
    val secretaria = remember {
        DataProvider.secretariaList
    }
    val docencia = remember {
        DataProvider.docenciaList
    }

    LazyColumn(
        contentPadding = PaddingValues(
            horizontal = 16.dp,
            vertical = 8.dp)) {
        item { SubTitleText(message = "DIRECCION") }
        items(
            items = direccion,
            itemContent = {
                StaffListItem(
                    personalAdmin = it, navigateToProfile)
            }
        )
        item { SubTitleText(message = "SECRETARIA") }
        items(
            items = secretaria,
            itemContent = {
                StaffListItem(
                    personalAdmin = it, navigateToProfile)
            }
        )
        item { SubTitleText(message = "DOCENTES") }
        items(
            items = docencia,
            itemContent = {
                StaffListItem(
                    personalAdmin = it, navigateToProfile)
            }
        )
    }
}


@Composable
fun SubTitleText(message: String, modifier: Modifier = Modifier){
    Column() {
        Text(text = message, fontSize = 36.sp, modifier = Modifier.padding(top = 16.dp))
    }
}