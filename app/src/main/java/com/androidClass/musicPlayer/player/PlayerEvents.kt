package com.androidClass.musicPlayer.player

import com.androidClass.musicPlayer.models.Track


interface PlayerEvents {


    fun onPlayPauseClick()


    fun onPreviousClick()

    fun onNextClick()

    fun onTrackClick(track: Track)

    fun onSeekBarPositionChanged(position: Long)
}
