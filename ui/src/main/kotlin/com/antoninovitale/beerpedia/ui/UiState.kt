package com.antoninovitale.beerpedia.ui

import com.antoninovitale.beerpedia.domain.Beer

internal sealed class UiState {

    object Empty : UiState()

    object Loading : UiState()

    data class Loaded(
        val beers: List<Beer>
    ) : UiState()

    object Error : UiState()
}
