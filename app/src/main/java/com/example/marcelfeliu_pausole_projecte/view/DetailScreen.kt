package com.example.marcelfeliu_pausole_projecte.view
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.marcelfeliu_pausole_projecte.viewmodel.RickAndMortyViewModel
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ColorMatrix
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun DetailScreen(navController: NavController, viewModel: RickAndMortyViewModel, modifier: Modifier = Modifier) {
    val character by viewModel.currentCharacter.observeAsState()

    val isCaptured by viewModel.isCaptured.observeAsState(false)

    LaunchedEffect(character) {
        character?.let {
            viewModel.isCaptured(it)
        }
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(1.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Button(
                onClick = {
                    if (isCaptured){
                        viewModel.freeCharacter(character!!) {
                            viewModel.toggleIsCapturingCharacter()
                        }
                    } else {
                        viewModel.captureCharacter(character!!) {
                            viewModel.toggleIsCapturingCharacter()
                        }
                    }
                }
            ) {
                if (isCaptured){
                    Text("Free")
                } else {
                    Text("Capture")
                }
            }
            if (character != null) {
                GlideImage(
                    model = character!!.image,
                    contentDescription = character!!.name,
                    colorFilter = ColorFilter.colorMatrix(ColorMatrix().apply
                    {
                        if (isCaptured) {
                            setToSaturation(0f)
                        } else {
                            setToSaturation(1f)
                        }
                    }),
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(380.dp)

                )

                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = character!!.name,
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Specie: ${character!!.species.lowercase().replaceFirstChar { it.uppercase() }}",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Medium,
                )

                Text(
                    text = "Status: ${character!!.status.lowercase().replaceFirstChar { it.uppercase() }}",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Medium,
                )

                Text(
                    text = "Gender: ${character!!.gender.lowercase().replaceFirstChar { it.uppercase() }}",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Medium,
                )

                Spacer(modifier = Modifier.height(24.dp))
            } else {
                Text(
                    text = "Unknown Character",
                    fontSize = 22.sp,
                    color = Color.Red,
                    fontWeight = FontWeight.Bold
                )
            }

            Button(
                onClick =
                    {
                        navController.popBackStack()
                    },
                modifier = Modifier.padding(top = 20.dp)
            ) {
                Text("Go Back")
            }
        }
    }
}
