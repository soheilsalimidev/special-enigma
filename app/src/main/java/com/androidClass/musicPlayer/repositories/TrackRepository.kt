package com.androidClass.musicPlayer.repositories

import com.androidClass.musicPlayer.models.Track

interface TrackRepository {


    fun getTrackList(): List<Track>
}
