package com.example.dynamicaudioplayer

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.dynamicaudioplayer.ui.composables.AButton
import com.example.dynamicaudioplayer.ui.viewModels.DynamicAudioPlayerViewModel

@Composable
fun DynamicAudioPlayer(
    modifier: Modifier = Modifier,
    viewModel: DynamicAudioPlayerViewModel = viewModel()
) {
    val audioUrl = viewModel.audioUrl.observeAsState().value

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = Color(0xFFF3F3F3))
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Column(
            modifier = Modifier
                .wrapContentSize(),
            verticalArrangement = Arrangement.spacedBy(30.dp, Alignment.Top),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            AButton(onClick = { viewModel.playAudio() }, text = "Play Audio file")
            AButton(onClick = { viewModel.pauseAudio() }, text = "Pause Audio")
            audioUrl?.let {
                Text("Audio URL: $it", style = TextStyle(fontSize = 14.sp))
            }
        }
    }
}