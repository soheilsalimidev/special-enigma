package com.androidClass.musicPlayer.repositories

import android.annotation.SuppressLint
import android.content.ContentUris
import android.content.Context
import android.os.Build
import android.provider.MediaStore
import android.util.Log
import android.util.Size
import androidx.annotation.RequiresApi
import com.androidClass.musicPlayer.models.Track
import java.io.FileNotFoundException
import javax.inject.Inject


/**
 * A concrete implementation of the [TrackRepository] interface.
 * This class is responsible for managing and providing tracks.
 *
 * @constructor Creates an instance of [TrackRepositoryImpl].
 */
@RequiresApi(Build.VERSION_CODES.Q)
class TrackRepositoryImpl @Inject constructor(context: Context) : TrackRepository {

    private val tracks = mutableListOf<Track>()

    init {
        // Initialize songs here or load from a data source
        createTracks(context)
    }

    override fun getTrackList(): List<Track> {
        return tracks
    }

    @RequiresApi(Build.VERSION_CODES.Q)
    @SuppressLint("Range", "Recycle", "SuspiciousIndentation")
    fun createTracks(context: Context) {
        val uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI
        val projection = arrayOf(
            MediaStore.Audio.AudioColumns.DATA,
            MediaStore.Audio.AudioColumns.ALBUM,
            MediaStore.Audio.ArtistColumns.ARTIST,
            MediaStore.Audio.AudioColumns.ALBUM_ID
        )
        val c = context.contentResolver.query(
            uri,
            projection,
            null,
           null,
            null
        )
        var i =0;
        if (c != null) {
            while (c.moveToNext()) {
                i++;
                val path = c.getString(0)
                val album = c.getString(1)
                val artist = c.getString(2)
                val name = path.substring(path.lastIndexOf("/") + 1)
                val albumId: Long =
                    c.getLong(c.getColumnIndexOrThrow(MediaStore.Audio.Media.ALBUM_ID))



           val track=     Track.Builder().trackName(name).artistName(artist).trackUrl(path).trackId(i)
                try {
                    val contentUri = ContentUris.withAppendedId(MediaStore.Audio.Albums.EXTERNAL_CONTENT_URI, albumId)
                    track.trackImage( context.contentResolver.loadThumbnail(contentUri, Size(512, 512), null))
                } catch(e: FileNotFoundException) {
                    Log.e("TAG", "createTracks: ", e)
                }

                tracks.add(track.build())
            }
            c.close()
        }
    }

}