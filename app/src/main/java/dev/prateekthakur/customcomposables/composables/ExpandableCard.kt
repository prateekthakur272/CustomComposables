package dev.prateekthakur.customcomposables.composables

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExpandableCard(title: String, content: String) {
    var isExpended by remember { mutableStateOf(true) }
    val rotate = animateFloatAsState(if(isExpended) 180f else 0f, label = "")
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .animateContentSize(
                animationSpec = tween(
                    durationMillis = 300,
                    easing = LinearOutSlowInEasing
                )
            ),
        colors = CardDefaults.cardColors(containerColor = Color(255,255,255,255)),
        onClick =  { isExpended = !isExpended }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(all = 16.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = title, style = TextStyle(fontSize = TextUnit(20.0f, TextUnitType.Sp), fontWeight = FontWeight.Bold))
                Icon(Icons.Filled.ArrowDropDown, contentDescription = "icon", modifier = Modifier.rotate(rotate.value))
            }
            if(isExpended){
                Text(text = content)
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun ExpandableCardPreview() {
    Box(modifier = Modifier.padding(all = 24.dp)){
        ExpandableCard("Hello Compose", "Although there are several composables you can use to create floating action buttons consistent with Material Design, their parameters don't differ greatly. Among the key parameters you should keep in mind are the following")
    }
}
