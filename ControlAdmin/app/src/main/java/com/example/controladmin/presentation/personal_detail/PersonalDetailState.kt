package com.example.controladmin.presentation.personal_detail

import com.example.controladmin.model.Personal

data class PersonalDetailState(
    val isLoading: Boolean = false,
    val personal: Personal? = null,
    val error: String = ""
)