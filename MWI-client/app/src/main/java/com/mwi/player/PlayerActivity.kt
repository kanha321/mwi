package com.mwi.player

import android.content.res.Configuration
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.WindowInsets
import android.view.WindowInsetsController
import android.view.WindowManager
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.SeekParameters
import com.google.android.exoplayer2.ui.StyledPlayerView
import com.mwi.R

class PlayerActivity : AppCompatActivity() {

    private val TAG = "PlayerActivity"

    private lateinit var player: ExoPlayer
    private lateinit var playerView: StyledPlayerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player)

        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)

        // Initialize the player view
        playerView = findViewById(R.id.player_view)

        // Get the video URL from the intent
        val videoUrl = intent.getStringExtra("video_url")
        Log.d(TAG, "onCreate: Video URL: $videoUrl")

        // Initialize ExoPlayer
        initializePlayer(videoUrl)
    }

    private fun initializePlayer(videoUrl: String?) {
        player = ExoPlayer.Builder(this).build()

        player.setSeekParameters(SeekParameters.CLOSEST_SYNC)

        // Bind the player to the view
        playerView.player = player

        // Create media item
        val mediaItem = MediaItem.fromUri(videoUrl ?: "")
        player.setMediaItem(mediaItem)

        // Prepare and start playback
        player.prepare()
        player.playWhenReady = true
    }

    @RequiresApi(Build.VERSION_CODES.R)
    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)

        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            hideSystemUI()
        } else {
            showSystemUI()
        }
    }

    @RequiresApi(Build.VERSION_CODES.R)
    private fun hideSystemUI() {
        val windowInsetsController = window.insetsController
        if (windowInsetsController != null) {
            windowInsetsController.hide(WindowInsets.Type.statusBars() or WindowInsets.Type.navigationBars())
            windowInsetsController.systemBarsBehavior =
                WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        }
    }

    @RequiresApi(Build.VERSION_CODES.R)
    private fun showSystemUI() {
        window.insetsController?.show(WindowInsets.Type.statusBars() or WindowInsets.Type.navigationBars())
    }


    override fun onPause() {
        super.onPause()
        player.pause()
    }

    override fun onStop() {
        super.onStop()
        player.release()
    }
}
