package com.example.marcelfeliu_pausole_projecte.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.marcelfeliu_pausole_projecte.model.Character

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun RMCharacterItem(character: Character, onSelectedItem: ()-> Unit) {
    Card(modifier = Modifier
        .fillMaxWidth()
        // Afegir propietat clickable i li definim el comportament
        .clickable { onSelectedItem() }
    ) {
        Row {
            GlideImage(
                model = character.image,
                contentDescription = character.name,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxHeight()
                    .size(80.dp)
            )
            Column {
                Text(
                    text = "Name: " + character.name,
                    fontSize = MaterialTheme.typography.bodyLarge.fontSize,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "Species: " + character.species.lowercase().replaceFirstChar { it.uppercase() }
                )
                Text(
                    text = "Status: " +character.status.lowercase().replaceFirstChar { it.uppercase() }
                )
            }
        }
    }
}
