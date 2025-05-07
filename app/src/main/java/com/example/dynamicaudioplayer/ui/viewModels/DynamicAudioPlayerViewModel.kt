package com.example.dynamicaudioplayer.ui.viewModels

import android.media.AudioAttributes
import android.media.MediaPlayer
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.FirebaseDatabase

class DynamicAudioPlayerViewModel : ViewModel() {
    private val _audioUrl = MutableLiveData<String>()
    val audioUrl: LiveData<String> = _audioUrl
    private var mediaPlayer: MediaPlayer? = null

    init {
        fetchAudioUrl()
    }

    private fun fetchAudioUrl() {
        val databaseReference = FirebaseDatabase.getInstance().getReference("url")
        databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                _audioUrl.value = snapshot.getValue(String::class.java)
            }

            override fun onCancelled(error: DatabaseError) {
                // Handle error
            }
        })
    }

    fun playAudio() {
        val url = _audioUrl.value ?: return
        mediaPlayer?.release()
        mediaPlayer = MediaPlayer().apply {
            setAudioAttributes(
                AudioAttributes.Builder()
                    .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                    .build()
            )
            setDataSource(url)
            prepare()
            start()
        }
    }

    fun pauseAudio() {
        mediaPlayer?.pause()
    }

    override fun onCleared() {
        super.onCleared()
        mediaPlayer?.release()
    }
}
