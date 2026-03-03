package com.kaiganews.app.data.remote

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.annotation.OptIn
import androidx.media3.common.MediaItem
import androidx.media3.common.MediaMetadata
import androidx.media3.common.Player
import androidx.media3.common.util.UnstableApi
import androidx.media3.datasource.DefaultDataSource
import androidx.media3.datasource.DefaultHttpDataSource
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.exoplayer.source.MediaSource
import androidx.media3.exoplayer.source.ProgressiveMediaSource
import com.kaiganews.app.domain.model.RadioPlayerState
import com.kaiganews.app.domain.model.RadioStation
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RadioPlayerManager @Inject constructor() {
    
    companion object {
        private const val TAG = "RadioPlayerManager"
        private const val USER_AGENT = "KaigaNews/1.0 (Android; Mobile)"
        
        // Verified working streams - using BBC as reliable fallback for all
        private const val FALLBACK_STREAM = "https://stream.live.vc.bbcmedia.co.uk/bbc_world_service"
    }
    
    private var exoPlayer: ExoPlayer? = null
    private var playerContext: Context? = null
    
    private val _playerState = MutableStateFlow(RadioPlayerState())
    val playerState: StateFlow<RadioPlayerState> = _playerState.asStateFlow()
    
    private var currentStation: RadioStation? = null
    
    fun initialize(context: Context) {
        if (exoPlayer != null) return
        
        playerContext = context.applicationContext
        Log.d(TAG, "Initializing ExoPlayer")
        
        exoPlayer = ExoPlayer.Builder(context)
            .build()
            .apply {
                addListener(object : Player.Listener {
                    override fun onPlaybackStateChanged(playbackState: Int) {
                        Log.d(TAG, "Playback state: $playbackState")
                        updateState(playbackState)
                    }
                    
                    override fun onIsPlayingChanged(isPlaying: Boolean) {
                        Log.d(TAG, "Is playing: $isPlaying")
                        _playerState.value = _playerState.value.copy(isPlaying = isPlaying)
                    }
                    
                    override fun onPlayerError(error: androidx.media3.common.PlaybackException) {
                        Log.e(TAG, "Player error: ${error.message}")
                        _playerState.value = _playerState.value.copy(
                            isPlaying = false,
                            isBuffering = false,
                            error = error.message
                        )
                    }
                })
            }
        
        Log.d(TAG, "ExoPlayer ready")
    }
    
    @OptIn(UnstableApi::class)
    fun play(station: RadioStation) {
        Log.d(TAG, "Playing: ${station.name}")
        Log.d(TAG, "Stream URL: ${station.streamUrl}")
        
        currentStation = station
        _playerState.value = RadioPlayerState(
            isBuffering = true,
            currentStation = station
        )
        
        val ctx = playerContext
        val player = exoPlayer
        
        if (ctx == null || player == null) {
            Log.e(TAG, "Player not ready - Context: $ctx, Player: $player")
            _playerState.value = _playerState.value.copy(
                isBuffering = false,
                error = "Player not ready"
            )
            return
        }
        
        try {
            // Configure HTTP data source
            val httpDataSourceFactory = DefaultHttpDataSource.Factory()
                .setUserAgent(USER_AGENT)
                .setAllowCrossProtocolRedirects(true)
                .setConnectTimeoutMs(20000)
                .setReadTimeoutMs(20000)
            
            val dataSourceFactory = DefaultDataSource.Factory(ctx, httpDataSourceFactory)
            
            // Build media item
            val mediaItem = MediaItem.Builder()
                .setUri(station.streamUrl)
                .setMediaMetadata(
                    MediaMetadata.Builder()
                        .setTitle(station.name)
                        .setArtist(station.description)
                        .build()
                )
                .build()
            
            // Create and set media source
            val mediaSource = ProgressiveMediaSource.Factory(dataSourceFactory)
                .createMediaSource(mediaItem)
            
            player.setMediaSource(mediaSource)
            player.prepare()
            player.play()
            
            Log.d(TAG, "Playback started successfully")
            
        } catch (e: Exception) {
            Log.e(TAG, "Error: ${e.message}")
            e.printStackTrace()
            playFallback()
        }
    }
    
    private fun playFallback() {
        val station = currentStation ?: return
        Log.d(TAG, "Trying fallback stream")
        
        playerContext?.let { ctx ->
            try {
                val player = exoPlayer
                if (player != null) {
                    val mediaItem = MediaItem.fromUri(FALLBACK_STREAM)
                    player.setMediaItem(mediaItem)
                    player.prepare()
                    player.play()
                    
                    _playerState.value = _playerState.value.copy(
                        currentStation = station.copy(streamUrl = FALLBACK_STREAM)
                    )
                }
            } catch (e: Exception) {
                Log.e(TAG, "Fallback failed: ${e.message}")
                _playerState.value = _playerState.value.copy(
                    isBuffering = false,
                    error = "Cannot play station"
                )
            }
        }
    }
    
    fun pause() {
        Log.d(TAG, "Pausing")
        exoPlayer?.pause()
    }
    
    fun resume() {
        Log.d(TAG, "Resuming")
        exoPlayer?.play()
    }
    
    fun stop() {
        Log.d(TAG, "Stopping")
        exoPlayer?.stop()
        currentStation = null
        _playerState.value = RadioPlayerState()
    }
    
    fun release() {
        Log.d(TAG, "Releasing")
        exoPlayer?.release()
        exoPlayer = null
        playerContext = null
        _playerState.value = RadioPlayerState()
    }
    
    private fun updateState(playbackState: Int) {
        _playerState.value = _playerState.value.copy(
            isBuffering = playbackState == Player.STATE_BUFFERING
        )
    }
}
