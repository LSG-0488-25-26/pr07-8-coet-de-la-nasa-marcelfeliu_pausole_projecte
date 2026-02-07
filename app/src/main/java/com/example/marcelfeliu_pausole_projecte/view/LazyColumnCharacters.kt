package com.example.marcelfeliu_pausole_projecte.view

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.marcelfeliu_pausole_projecte.model.Data
import com.example.marcelfeliu_pausole_projecte.model.Info
import com.example.marcelfeliu_pausole_projecte.viewmodel.RickAndMortyViewModel
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import com.example.marcelfeliu_pausole_projecte.R
@Composable
fun LazyColumnCharacters(navController: NavController, viewModel: RickAndMortyViewModel){
    val showLoading: Boolean by viewModel.loading.observeAsState(true)
    val characterList by viewModel.charactersData.observeAsState(Data(Info(0,"", 0, ""), emptyList()))
    val showSearchBar by viewModel.showSearchBar.observeAsState(false)

    val configuration = LocalConfiguration.current

    LaunchedEffect(Unit) {
        if (characterList.results.isEmpty())
        {
            viewModel.getCharacters()
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.backgroundimage2),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize(),
            alpha = 0.9f
        )

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
        ) {
            if (showLoading) {
                Row(
                    modifier = Modifier.fillMaxSize(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    CircularProgressIndicator(
                        color = MaterialTheme.colorScheme.secondary
                    )
                }
            } else{
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(4.dp),
                    contentPadding = PaddingValues(horizontal = 0.dp, vertical = 0.dp),
                    modifier = Modifier.fillMaxHeight()
                ) {
                    // poso el titol dins de la lazy column per que no sigui estacionari i poguem amagarlo
                    item {
                        if (configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
                            if (showSearchBar) {
                                MySearchBarView(viewModel)
                            }
                            Text(
                                text = "Character List",
                                fontSize = 32.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = Color(0xFF00FF9C),
                                textAlign = TextAlign.Center,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 12.dp)
                            )
                        }
                    }
                    // carreguem les cards reals
                    items(characterList.results) { char ->
                        RMCharacterItem(char) {
                            viewModel.setCurrentCharacter(char)
                            navController.navigate("DetailScreen")
                        }
                    }
                }
            }
        }
    }
}