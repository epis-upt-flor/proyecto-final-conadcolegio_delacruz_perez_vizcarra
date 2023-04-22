package com.example.controladmin

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.unit.dp

@Composable
fun ColegioRAStaff(navigateToProfile: (PersonalAdmin) -> Unit) {
    val personal = remember {
        DataProvider.personalList
    }
    LazyColumn(contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)) {
        items(items = personal, itemContent = {
                StaffListItem(personalAdmin = it, navigateToProfile)
        })
    }
}