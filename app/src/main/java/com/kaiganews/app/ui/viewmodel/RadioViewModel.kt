package com.kaiganews.app.ui.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kaiganews.app.data.local.RadioStationsData
import com.kaiganews.app.data.remote.RadioPlayerManager
import com.kaiganews.app.domain.model.RadioPlayerState
import com.kaiganews.app.domain.model.RadioStation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class RadioViewModel @Inject constructor(
    private val radioPlayerManager: RadioPlayerManager
) : ViewModel() {
    
    val stations: List<RadioStation> = RadioStationsData.ugandanRadioStations
    
    val playerState: StateFlow<RadioPlayerState> = radioPlayerManager.playerState
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = RadioPlayerState()
        )
    
    fun initializePlayer(context: Context) {
        radioPlayerManager.initialize(context)
    }
    
    fun playStation(station: RadioStation) {
        radioPlayerManager.play(station)
    }
    
    fun togglePlayPause() {
        if (playerState.value.isPlaying) {
            radioPlayerManager.pause()
        } else {
            radioPlayerManager.resume()
        }
    }
    
    fun stopPlayback() {
        radioPlayerManager.stop()
    }
    
    override fun onCleared() {
        super.onCleared()
    }
}
