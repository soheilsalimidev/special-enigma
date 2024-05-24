@file:OptIn(ExperimentalMaterialApi::class, ExperimentalMaterial3Api::class)

package com.androidClass.musicPlayer.ui.composable

import android.content.Intent
import android.graphics.Bitmap
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.androidClass.musicPlayer.R
import com.androidClass.musicPlayer.models.Track
import com.androidClass.musicPlayer.player.PlaybackState
import com.androidClass.musicPlayer.player.PlayerEvents
import com.androidClass.musicPlayer.ui.activities.PlayerActivity2
import com.androidClass.musicPlayer.ui.theme.md_theme_light_outline
import com.androidClass.musicPlayer.ui.theme.typography
import com.androidClass.musicPlayer.viewmodels.HomeViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.io.ByteArrayOutputStream


/**
 * A composable function that hosts the home screen of the application.
 * It manages the state of the bottom sheet and when to show it.
 *
 * @param viewModel The ViewModel that is responsible for providing data to the UI and processing user actions.
 */
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HomeScreenParent(viewModel: HomeViewModel) {
    val fullScreenState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden, skipHalfExpanded = true
    )
    val scope = rememberCoroutineScope()
    val onBottomTabClick: () -> Unit = { scope.launch { fullScreenState.show() } }
    val context = LocalContext.current
    TrackList(
        tracks = viewModel.tracks,
        selectedTrack = viewModel.selectedTrack,
        fullScreenState = fullScreenState,
        playerEvents = viewModel,
        playbackState = viewModel.playbackState,
        onBottomTabClick = onBottomTabClick,
        onImageClick = {
            context. startActivity(Intent(context, PlayerActivity2::class.java).apply {
                putExtra("trackUrl", viewModel.selectedTrack?.trackUrl)
                putExtra("artistName", viewModel.selectedTrack?.artistName)
                val stream = ByteArrayOutputStream()
                viewModel.selectedTrack?.trackImage?.compress(Bitmap.CompressFormat.PNG, 100, stream)
                putExtra("trackImage", stream.toByteArray())
                putExtra("trackName", viewModel.selectedTrack?.trackName)



            })
        }
    )
}

@Composable
fun TrackList(
    tracks: List<Track>,
    selectedTrack: Track?,
    fullScreenState: ModalBottomSheetState,
    playerEvents: PlayerEvents,
    playbackState: StateFlow<PlaybackState>,
    onBottomTabClick: () -> Unit,
    onImageClick:()->Unit
) {
    ModalBottomSheetLayout(
        sheetContent = {
            if (selectedTrack != null) BottomSheetDialog(
                selectedTrack, playerEvents, playbackState
            )
        },
        sheetState = fullScreenState,
        sheetShape = RoundedCornerShape(topStart = 10.dp, topEnd = 10.dp),
        sheetElevation = 8.dp
    ) {
        Scaffold(topBar = {
            Surface(shadowElevation = 5.dp) {
                CenterAlignedTopAppBar(
                    title = {
                        Text(
                            text = stringResource(id = R.string.app_title),
                            style = typography.titleLarge,
                            color = Color.Black,
                            fontSize = 27.sp,
                        )

                    },
                    colors = TopAppBarDefaults.centerAlignedTopAppBarColors(containerColor = md_theme_light_outline)
                )
            }
        }) { paddingValues ->
            Box(modifier = Modifier.padding(top = paddingValues.calculateTopPadding())) {
                Column {
                    LazyColumn(
                        modifier = Modifier.weight(weight = 1f),
                        contentPadding = PaddingValues(5.dp)
                    ) {
                        items(tracks) {
                            TrackListItem(
                                track = it,
                                onTrackClick = { playerEvents.onTrackClick(it) })
                        }
                    }
                    AnimatedVisibility(
                        visible = selectedTrack != null,
                        enter = slideInVertically(initialOffsetY = { fullHeight -> fullHeight })
                    ) {
                        BottomPlayerTab(
                            selectedTrack!!,
                            playerEvents,
                            onBottomTabClick,
                            onImageClick
                        )
                    }
                }
            }
        }
    }
}