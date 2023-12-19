package com.antoninovitale.beerpedia.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.antoninovitale.beerpedia.common.IDispatcherProvider
import com.antoninovitale.beerpedia.domain.IGetBeersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class BeerViewModel @Inject constructor(
    private val dispatchers: IDispatcherProvider,
    private val useCase: IGetBeersUseCase
) : ViewModel() {

    private val _state: MutableStateFlow<UiState> = MutableStateFlow(UiState.Empty)
    val state: StateFlow<UiState> = _state.asStateFlow()

    // start can be called explicitly when the first screen is launched eventually.
    init {
        start()
    }

    fun start() {
        viewModelScope.launch(dispatchers.io()) {
            try {
                _state.update { UiState.Loading }
                val data = useCase.execute()
                _state.update { UiState.Loaded(data) }
            } catch (ex: Exception) {
                _state.update { UiState.Error }
            }
        }
    }
}
