package com.example.marcelfeliu_pausole_projecte

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.marcelfeliu_pausole_projecte.nav.EntryPoint
import com.example.marcelfeliu_pausole_projecte.ui.theme.MarcelFeliu_PauSole_ProjecteTheme
import com.example.marcelfeliu_pausole_projecte.viewmodel.RickAndMortyViewModel
import kotlin.getValue

class MainActivity : ComponentActivity() {
    val questionViewModel: RickAndMortyViewModel by viewModels<RickAndMortyViewModel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MarcelFeliu_PauSole_ProjecteTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val navigationController = rememberNavController()
                    EntryPoint(navigationController, questionViewModel)

                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MarcelFeliu_PauSole_ProjecteTheme {
        Greeting("Android")
    }
}