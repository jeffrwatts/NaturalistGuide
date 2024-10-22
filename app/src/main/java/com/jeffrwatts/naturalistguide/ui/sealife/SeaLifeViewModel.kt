package com.jeffrwatts.naturalistguide.ui.sealife

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jeffrwatts.naturalistguide.data.species.Species
import com.jeffrwatts.naturalistguide.data.species.SpeciesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject



@HiltViewModel
class SeaLifeViewModel @Inject constructor(
    private val speciesRepository: SpeciesRepository
) : ViewModel() {

    val speciesList: StateFlow<List<Species>> = speciesRepository.getSpecies()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )
}
