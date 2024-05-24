package com.androidClass.musicPlayer.ui.activities

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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

/**
 * [MainActivity] is the main entry point of the app.
 * It is annotated with [@AndroidEntryPoint] to enable field injection via Hilt.
 * This class extends [ComponentActivity], which is a lean version of [AppCompatActivity].
 */
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    /**
     * The [HomeViewModel] instance is obtained via the [viewModels] delegate,
     * which uses the activity as the ViewModelStoreOwner.
     */
    private val viewModel: HomeViewModel by viewModels()
    private val PermissionsRequestCode = 123

    /**
     * The [onCreate] method is called when the activity is starting.
     * It sets up the UI content and associates it with the activity.
     *
     * @param savedInstanceState If the activity is being re-initialized after previously being shut down
     * then this Bundle contains the data it most recently supplied in [onSaveInstanceState].
     * Otherwise it is null.
     */
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.READ_MEDIA_AUDIO)
            != PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(this,
                arrayOf(Manifest.permission.READ_MEDIA_AUDIO),
                PermissionsRequestCode);
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int,
                                            permissions: Array<String>,
                                            grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this@MainActivity, "audio Permission Granted", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this@MainActivity, "audio Permission Denied", Toast.LENGTH_SHORT).show()
            }

    }
}