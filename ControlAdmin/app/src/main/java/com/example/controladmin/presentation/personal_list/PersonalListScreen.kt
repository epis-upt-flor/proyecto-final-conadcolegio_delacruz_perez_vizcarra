package com.example.controladmin.presentation.personal_list

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.controladmin.presentation.personal_list.components.PersonalList
import com.example.controladmin.ui.theme.Red100

@Composable
fun PersonalListScreen(
    navigateToPersonalDetail: () -> Unit,
    isRefreshing: Boolean,
    refreshData: () -> Unit,
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        floatingActionButton = {
            FloatingActionButton(
                onClick = navigateToPersonalDetail,
                backgroundColor = Red100,
                contentColor = Color.White
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add Icon")
            }
        }
    ) {
        PersonalList(
            isRefreshing = isRefreshing,
            refreshData = refreshData
        )
    }
}