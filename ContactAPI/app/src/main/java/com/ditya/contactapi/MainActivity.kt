package com.ditya.contactapi

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.ditya.contactapi.ui.theme.ContactAPITheme
import com.ditya.contactapi.ui.theme.mainscreen.MainScreen
import com.ditya.contactapi.ui.theme.mainscreen.MainViewModel

class MainActivity : ComponentActivity() {

    val mainViewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ContactAPITheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    mainViewModel.getUserList()
                    MainScreen(viewModel = mainViewModel)
                    Log.e("DEBUG", mainViewModel.errorMessage)

                }
            }
        }
    }
}
