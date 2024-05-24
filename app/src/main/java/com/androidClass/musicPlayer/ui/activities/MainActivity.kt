package com.androidClass.musicPlayer.ui.activities

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.androidClass.musicPlayer.ui.composable.HomeScreenParent
import com.androidClass.musicPlayer.ui.theme.MusicPlayerJetpackComposeTheme
import com.androidClass.musicPlayer.viewmodels.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {


    private val viewModel: HomeViewModel by viewModels()


    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val requestPermissionLauncher =
            registerForActivityResult(
                ActivityResultContracts.RequestPermission()
            ) { isGranted: Boolean ->
                if (!isGranted) {
                    Toast.makeText(this@MainActivity, "audio Permission Denied", Toast.LENGTH_SHORT)
                        .show()
                }
            }

        when {
            ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.READ_MEDIA_AUDIO
            ) == PackageManager.PERMISSION_GRANTED -> {

            }

            ActivityCompat.shouldShowRequestPermissionRationale(
                this, Manifest.permission.READ_MEDIA_AUDIO

            ) -> {
                Toast.makeText(this@MainActivity, "audio Permission Denied", Toast.LENGTH_SHORT)
                    .show()
            }

            else -> {
                requestPermissionLauncher.launch(
                    Manifest.permission.READ_MEDIA_AUDIO
                )
            }
        }

        setContent {
            // Set the theme of the app to MusicPlayerJetpackComposeTheme.
            MusicPlayerJetpackComposeTheme {
                // Create a surface container using the Surface Composable.
                Surface(modifier = Modifier.fillMaxSize()) {
                    // Setup the HomeScreenParent with the viewModel.
                    HomeScreenParent(viewModel)
                }
            }
        }
    }

}