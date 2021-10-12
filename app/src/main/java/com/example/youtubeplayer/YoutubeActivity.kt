package com.example.youtubeplayer

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.google.android.youtube.player.YouTubePlayerView

const val YOUTUBE_VIDEO_ID = "ElpitAfkRS4"
const val YOUTUBE_PLAYLIST="PLXtTjtWmQhg1SsviTmKkWO5n0a_-T0bnD"
class YoutubeActivity : YouTubeBaseActivity(), YouTubePlayer.OnInitializedListener {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_youtube)
        //val layout = findViewById<ConstraintLayout>(R.id.activity_youtube)
        val layout = layoutInflater.inflate(R.layout.activity_youtube, null) as ConstraintLayout
        setContentView(layout)

        //val button1 = Button(this)
        //button1.layoutParams = ConstraintLayout.LayoutParams(600, 180)
        //button1.text = "Test button"
        //layout.addView(button1)

        val playerView = YouTubePlayerView(this)
        playerView.layoutParams = ConstraintLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)

        layout.addView(playerView)

        playerView.initialize(getString(R.string.GOOGLE_API_KEY), this)
    }

    override fun onInitializationSuccess(
        provider: YouTubePlayer.Provider?,
        youtubePlayer: YouTubePlayer?,
        wasRestored: Boolean
    ) {
        Log.d(TAG, "On init success")
        Toast.makeText(this, "successfully init", Toast.LENGTH_SHORT).show()

        youtubePlayer?.setPlayerStateChangeListener(playerStateChangeListener)
        youtubePlayer?.setPlaybackEventListener(playbackEventListener)
        if(!wasRestored){
            youtubePlayer?.loadVideo(YOUTUBE_VIDEO_ID)
        }
        else{
            youtubePlayer?.play()
        }
    }

    override fun onInitializationFailure(
        provider: YouTubePlayer.Provider?,
        youTubeInitializationResult: YouTubeInitializationResult?
    ) {
        val REQUEST_CODE = 0
        if(youTubeInitializationResult?.isUserRecoverableError==true){
            youTubeInitializationResult.getErrorDialog(this, REQUEST_CODE).show()
        }
        else{
            val errorMsg = "Error initializing ytPlayer"
            Toast.makeText(this, errorMsg, Toast.LENGTH_LONG).show()
        }
    }

    private val playbackEventListener = object :YouTubePlayer.PlaybackEventListener{
        override fun onPlaying() {
            Toast.makeText(this@YoutubeActivity, "Video playing", Toast.LENGTH_SHORT).show()
        }

        override fun onPaused() {
            Toast.makeText(this@YoutubeActivity, "Video paused", Toast.LENGTH_SHORT).show()

        }

        override fun onStopped() {
            Toast.makeText(this@YoutubeActivity, "Video stopped", Toast.LENGTH_SHORT).show()

        }

        override fun onBuffering(p0: Boolean) {
            Toast.makeText(this@YoutubeActivity, "test", Toast.LENGTH_SHORT).show()

        }

        override fun onSeekTo(p0: Int) {
            Toast.makeText(this@YoutubeActivity, "test", Toast.LENGTH_SHORT).show()
        }
    }

    private val playerStateChangeListener=object:YouTubePlayer.PlayerStateChangeListener{
        override fun onLoading() {
            Toast.makeText(this@YoutubeActivity, "test", Toast.LENGTH_SHORT).show()
        }

        override fun onLoaded(p0: String?) {
            Toast.makeText(this@YoutubeActivity, "test", Toast.LENGTH_SHORT).show()

        }

        override fun onAdStarted() {
            Toast.makeText(this@YoutubeActivity, "Fucking Ad", Toast.LENGTH_SHORT).show()
        }

        override fun onVideoStarted() {
            Toast.makeText(this@YoutubeActivity, "Video started", Toast.LENGTH_SHORT).show()
        }

        override fun onVideoEnded() {
            Toast.makeText(this@YoutubeActivity, "Video ended", Toast.LENGTH_SHORT).show()
        }

        override fun onError(p0: YouTubePlayer.ErrorReason?) {
            Toast.makeText(this@YoutubeActivity, "test", Toast.LENGTH_SHORT).show()
        }
    }
}