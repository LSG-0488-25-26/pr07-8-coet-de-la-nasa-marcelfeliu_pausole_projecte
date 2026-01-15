package com.example.marcelfeliu_pausole_projecte.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.marcelfeliu_pausole_projecte.R
import com.example.marcelfeliu_pausole_projecte.model.RMCharacter
import com.example.marcelfeliu_pausole_projecte.viewmodel.RickAndMortyViewModel

@Composable
fun LazyColumnCharacters(navController: NavController, viewModel: RickAndMortyViewModel){
    val characterList by viewModel.characters.observeAsState(mutableListOf())

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize().safeDrawingPadding()
    ) {
        Text(
            text = "Character List",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold
        )

        LazyColumn(verticalArrangement = Arrangement.spacedBy(4.dp),
            modifier = Modifier
                .padding(vertical = 30.dp)
                .fillMaxHeight()
        ) {
            items(characterList){  char ->
                RMCharacterItem(char){
                    viewModel.setCurrentCharacter(char)
                    navController.navigate("DetailScreen")
                }
            }
        }
    }


}