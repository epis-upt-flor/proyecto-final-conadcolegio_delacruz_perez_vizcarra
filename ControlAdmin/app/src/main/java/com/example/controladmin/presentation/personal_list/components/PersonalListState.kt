package com.example.controladmin.presentation.personal_list.components

import com.example.controladmin.model.Personal

data class PersonalListState(
    val isLoading: Boolean = false,
    val personal: List<Personal> = emptyList(),
    val error: String = ""
)

