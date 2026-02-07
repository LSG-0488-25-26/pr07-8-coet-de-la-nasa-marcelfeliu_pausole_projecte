package com.example.marcelfeliu_pausole_projecte.view

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.marcelfeliu_pausole_projecte.model.Character
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun RMCharacterItem(character: Character, onSelectedItem: () -> Unit) {

    val statusColor = when (character.status) {
        "Alive" -> Color(0xFF00FF9C)
        "Dead" -> Color(0xFFFF4C4C)
        else -> Color(0xFFFFD166)
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 6.dp)
            .shadow(
                elevation = 20.dp,
                shape = RoundedCornerShape(20.dp),
                ambientColor = Color(0xFF00FF9C),
                spotColor = Color(0xFF00FF9C)
            )
            .clickable { onSelectedItem() },
        shape = androidx.compose.foundation.shape.RoundedCornerShape(20.dp),
        colors = androidx.compose.material3.CardDefaults.cardColors(
            containerColor = Color(0xFF1F2A32)),
        elevation = androidx.compose.material3.CardDefaults.cardElevation(
            defaultElevation = 10.dp
        )
    ) {
        Row {
            GlideImage(
                model = character.image,
                contentDescription = character.name,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .padding(10.dp)
                    .size(80.dp)
                    .clip(androidx.compose.foundation.shape.CircleShape)
                    .border(
                        width = 2.dp,
                        color = Color(0xFF00FF9C),
                        shape = androidx.compose.foundation.shape.CircleShape
                    )
            )

            Column(modifier = Modifier.padding(vertical = 10.dp)) {
                Text(
                    text = character.name,
                    fontSize = MaterialTheme.typography.bodyLarge.fontSize,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF00FF9C)
                )
                Text(
                    text = character.species,
                    color = Color.White
                )


                Box(
                    modifier = Modifier
                        .padding(top = 6.dp)
                        .background(statusColor, androidx.compose.foundation.shape.RoundedCornerShape(50))
                        .padding(horizontal = 12.dp, vertical = 4.dp)
                ) {
                    Text(
                        text = character.status.uppercase(),
                        color = Color.Black,
                        fontWeight = FontWeight.Bold,
                        fontSize = MaterialTheme.typography.labelMedium.fontSize
                    )
                }
            }
        }
    }
}
