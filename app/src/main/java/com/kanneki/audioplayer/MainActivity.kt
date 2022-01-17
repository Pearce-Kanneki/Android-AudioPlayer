package com.kanneki.audioplayer

import android.media.MediaPlayer
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.kanneki.audioplayer.components.AudioPlayer
import com.kanneki.audioplayer.ui.theme.AudioPlayerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AudioPlayerTheme {
                Surface(color = MaterialTheme.colors.background) {
                    val mediaPlayer: MediaPlayer = MediaPlayer.create(
                        this,
                        R.raw.sample_12s
                    )

                    AudioPlayer(mediaPlayer)
                }
            }
        }
    }
}
