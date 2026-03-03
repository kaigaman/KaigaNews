package com.kaiganews.app.domain.model

data class RadioStation(
    val id: String,
    val name: String,
    val streamUrl: String,
    val logoUrl: String?,
    val description: String,
    val frequency: String? = null,
    val isLive: Boolean = true
)

data class RadioPlayerState(
    val isPlaying: Boolean = false,
    val currentStation: RadioStation? = null,
    val isBuffering: Boolean = false,
    val error: String? = null
)
