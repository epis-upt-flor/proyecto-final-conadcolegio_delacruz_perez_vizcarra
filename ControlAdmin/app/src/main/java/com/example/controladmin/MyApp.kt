package com.example.controladmin

import android.annotation.SuppressLint
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MyApp(navigateToProfile:(PersonalAdmin) -> Unit)
{
    Scaffold(
        content = {
            ColegioRAStaff(navigateToProfile)
        }
    )
}