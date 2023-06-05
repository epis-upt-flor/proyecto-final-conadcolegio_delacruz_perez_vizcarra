package com.example.controladmin.navigation

import androidx.navigation.NamedNavArgument

sealed class Destination(
    val route: String,
    val arguments: List<NamedNavArgument>
){
    object PersonalList : Destination("personalList", emptyList())
    object PersonalDetail: Destination("personalDetail", emptyList())
}