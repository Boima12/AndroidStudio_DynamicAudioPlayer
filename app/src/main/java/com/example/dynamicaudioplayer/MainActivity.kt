package com.example.dynamicaudioplayer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.dynamicaudioplayer.ui.theme.DynamicAudioPlayerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DynamicAudioPlayer()
        }
    }
}



@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    DynamicAudioPlayerTheme {
        DynamicAudioPlayer()
    }
}