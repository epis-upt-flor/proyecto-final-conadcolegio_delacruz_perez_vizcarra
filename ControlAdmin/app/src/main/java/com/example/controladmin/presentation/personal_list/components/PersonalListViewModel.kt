package com.example.controladmin.presentation.personal_list.components

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.controladmin.repositories.PersonalRepository
import com.example.controladmin.repositories.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class PersonalListViewModel
@Inject
constructor(
    private val personalRepository: PersonalRepository
): ViewModel() {
    private val _state: MutableState<PersonalListState> = mutableStateOf(PersonalListState())
    val state: State<PersonalListState> = _state

    private val _isRefreshing = MutableStateFlow(false)
    val isRefreshing: StateFlow<Boolean> = _isRefreshing

    init {
        getPersonalList()
    }

    fun getPersonalList(){
        personalRepository.getPersonalList().onEach { result ->
            when(result) {
                is Result.Error -> {
                    _state.value = PersonalListState(error = result.message ?: "Error Inesperado")
                }
                is Result.Loading -> {
                    _state.value = PersonalListState(isLoading = true)
                }
                is Result.Success ->{
                    _state.value = PersonalListState(personal = result.data ?: emptyList())
                }
            }
        }.launchIn(viewModelScope)
    }
}