package com.kanneki.audioplayer.components

import android.media.MediaPlayer
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.kanneki.audioplayer.viewmodel.MediaPlayerViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun PlayerButtons(
    modifier: Modifier = Modifier,
    mediaPlayer: MediaPlayer,
    playerButtonSize: Dp = 72.dp,
    sideButtonSize: Dp = 42.dp
) {
    val viewModel: MediaPlayerViewModel = viewModel()
    val scope = rememberCoroutineScope()
    val audioFinish = viewModel.audioFinish.collectAsState()
    val audioFlag = remember { mutableStateOf(true) }

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        val buttonModifier = Modifier
            .size(sideButtonSize)
            .semantics { role = Role.Button }

        Image(
            imageVector = Icons.Filled.SkipPrevious,
            contentDescription = "Skip Icon",
            contentScale = ContentScale.Fit,
            colorFilter = ColorFilter.tint(Color.White),
            modifier = buttonModifier
        )
        Image(
            imageVector = Icons.Filled.Replay10,
            contentDescription = "Replay 10 Sec Icon",
            contentScale = ContentScale.Fit,
            colorFilter = ColorFilter.tint(Color.White),
            modifier = buttonModifier
        )
        Image(
            imageVector = if (!audioFinish.value) {
                if (audioFlag.value) {
                    Icons.Filled.PlayCircleFilled
                } else {
                    Icons.Filled.PauseCircleFilled
                }
            } else {
                Icons.Filled.PlayCircleFilled
            },
            contentDescription = "Play / Pause Icon",
            contentScale = ContentScale.Fit,
            colorFilter = ColorFilter.tint(Color.White),
            modifier = Modifier
                .size(playerButtonSize)
                .semantics { role = Role.Button }
                .clickable {
                    if (audioFlag.value) {
                        mediaPlayer.start()
                        scope.launch {
                            delay(500)
                            viewModel.getMediaDuration(mediaPlayer)
                        }
                        audioFlag.value = false
                    } else {
                        audioFlag.value = true
                        mediaPlayer.pause()
                    }
                }
        )
        Image(
            imageVector = Icons.Filled.Forward10,
            contentDescription = "Forward Icon",
            contentScale = ContentScale.Fit,
            colorFilter = ColorFilter.tint(Color.White),
            modifier = buttonModifier
        )

        Image(
            imageVector = Icons.Filled.SkipNext,
            contentDescription = "Next Icon",
            contentScale = ContentScale.Fit,
            colorFilter = ColorFilter.tint(Color.White),
            modifier = buttonModifier
        )

    }

}