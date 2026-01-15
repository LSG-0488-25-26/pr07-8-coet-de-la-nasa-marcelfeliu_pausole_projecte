package com.example.marcelfeliu_pausole_projecte.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.marcelfeliu_pausole_projecte.model.RMCharacter

@Composable
fun RMCharacterItem(character: RMCharacter, onSelectedItem: ()-> Unit) {
    Card(modifier = Modifier
            .fillMaxWidth()
            // Afegir propietat clickable i li definim el comportament
            .clickable { onSelectedItem() }
    ) {
        Row {
            Image(
                painter = painterResource(id = character.image),
                contentDescription = character.name,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxHeight()
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
