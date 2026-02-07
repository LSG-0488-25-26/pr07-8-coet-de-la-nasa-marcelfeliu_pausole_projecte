package com.example.marcelfeliu_pausole_projecte.view
import android.app.Activity
import android.content.Context
import android.content.res.Configuration
import android.view.WindowManager
import android.widget.ScrollView
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.marcelfeliu_pausole_projecte.viewmodel.RickAndMortyViewModel


@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun DetailScreen(navController: NavController, viewModel: RickAndMortyViewModel, modifier: Modifier = Modifier) {
    val character by viewModel.currentCharacter.observeAsState()

    val isCaptured by viewModel.isCaptured.observeAsState(false)

    //portrait / landscape
    //https://www.geeksforgeeks.org/kotlin/detect-screen-orientation-in-android-using-jetpack-compose/
    val configuration = LocalConfiguration.current

    LaunchedEffect(character) {
        character?.let {
            viewModel.isCaptured(it)
        }
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFF0E1B1F))
            .verticalScroll(rememberScrollState()),
        contentAlignment = Alignment.Center
    ) {
        if (configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                if (character != null) {
                    GlideImage(
                        model = character!!.image,
                        contentDescription = character!!.name,
                        colorFilter = ColorFilter.colorMatrix(
                            ColorMatrix().apply {
                                if (isCaptured) setToSaturation(0f) else setToSaturation(1f)
                            }
                        ),
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(320.dp)
                            .clip(RoundedCornerShape(20.dp))
                            .border(3.dp, Color(0xFF00FF9C), RoundedCornerShape(20.dp))
                    )


                    Spacer(modifier = Modifier.height(10.dp))

                    Text(
                        text = character!!.name,
                        fontSize = 30.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF00FF9C)
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "Specie: ${character!!.species.lowercase().replaceFirstChar { it.uppercase() }}",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color(0xFFE6F7F2)

                    )

                    Text(
                        text = "Status: ${character!!.status.lowercase().replaceFirstChar { it.uppercase() }}",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color(0xFFE6F7F2)

                    )

                    Text(
                        text = "Gender: ${character!!.gender.lowercase().replaceFirstChar { it.uppercase() }}",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color(0xFFE6F7F2)

                    )

                    Text(
                        text = "Location: ${character!!.location.name.lowercase().replaceFirstChar { it.uppercase() }}",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color(0xFFE6F7F2)

                    )

                    Text(
                        text = "Origin: ${character!!.origin.name.lowercase().replaceFirstChar { it.uppercase() }}",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color(0xFFE6F7F2)

                    )

                    Text(
                        text = "Nº of episodes: ${character!!.episode.count()}",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color(0xFFE6F7F2)

                    )


                    Spacer(modifier = Modifier.height(5.dp))
                } else {
                    Text(
                        text = "Unknown Character",
                        fontSize = 22.sp,
                        color = Color.Red,
                        fontWeight = FontWeight.Bold
                    )
                }

                Row()
                {
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
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF00FF9C),
                            contentColor = Color.Black
                        )
                    ) {
                        Text(if (isCaptured) "Free" else "Capture")
                    }

                    Spacer(modifier = Modifier.width(20.dp))
                    Button(
                        onClick = { navController.popBackStack() },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF1F2A32),
                            contentColor = Color(0xFF00FF9C)
                        ),
                        modifier = Modifier.border(
                            1.5.dp,
                            Color(0xFF00FF9C),
                            RoundedCornerShape(50)
                        )
                    ) {
                        Text("Go Back")
                    }

                }
            }
        } else {
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
                modifier = modifier
                .fillMaxSize(1f)
            ) {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = modifier
                    .fillMaxWidth(0.5f)
                        .fillMaxHeight()
                )
                {
                    if (character != null) {
                        GlideImage(
                            model = character!!.image,
                            contentDescription = character!!.name,
                            colorFilter = ColorFilter.colorMatrix(
                                ColorMatrix().apply
                                {
                                    if (isCaptured) {
                                        setToSaturation(0f)
                                    } else {
                                        setToSaturation(1f)
                                    }
                                }),
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .size(180.dp)

                        )

                    }
                }

                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = modifier
                        .fillMaxWidth(1f)
                        .fillMaxHeight()
                ) {
                    if (character != null) {

                        Text(
                            text = character!!.name,
                            fontSize = 28.sp,
                            fontWeight = FontWeight.Bold
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        Row() {
                            Column(modifier = Modifier.fillMaxSize(0.5f)) {
                                Text(
                                    text = "Specie: ${
                                        character!!.species.lowercase().replaceFirstChar { it.uppercase() }
                                    }",
                                    fontSize = 20.sp,
                                    fontWeight = FontWeight.Medium,
                                )

                                Text(
                                    text = "Status: ${
                                        character!!.status.lowercase().replaceFirstChar { it.uppercase() }
                                    }",
                                    fontSize = 20.sp,
                                    fontWeight = FontWeight.Medium,
                                )

                                Text(
                                    text = "Gender: ${
                                        character!!.gender.lowercase().replaceFirstChar { it.uppercase() }
                                    }",
                                    fontSize = 20.sp,
                                    fontWeight = FontWeight.Medium,
                                )
                            }
                            Column (modifier = Modifier.fillMaxSize(1f)) {
                                Text(
                                    text = "Location: ${character!!.location.name.lowercase().replaceFirstChar { it.uppercase() }}",
                                    fontSize = 20.sp,
                                    fontWeight = FontWeight.Medium,
                                )

                                Text(
                                    text = "Origin: ${character!!.origin.name.lowercase().replaceFirstChar { it.uppercase() }}",
                                    fontSize = 20.sp,
                                    fontWeight = FontWeight.Medium,
                                )

                                Text(
                                    text = "N of episodes: ${character!!.episode.count()}",
                                    fontSize = 20.sp,
                                    fontWeight = FontWeight.Medium,
                                )
                            }
                        }
                        Spacer(modifier = Modifier.height(5.dp))
                    } else {
                        Text(
                            text = "Unknown Character",
                            fontSize = 22.sp,
                            color = Color.Red,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    Row()
                    {
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
                        Spacer(modifier = Modifier.width(20.dp))
                        Button(
                            onClick =
                                {
                                    navController.popBackStack()
                                },
                        ) {
                            Text("Go Back")
                        }
                    }

                }
            }
        }
    }
}