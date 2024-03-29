package dev.prateekthakur.customcomposables

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import dev.prateekthakur.customcomposables.composables.CircularIndicator
import dev.prateekthakur.customcomposables.ui.theme.CustomComposablesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CustomComposablesTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    HomeScreen()
                }
            }
        }
    }
}


@Composable
fun HomeScreen() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        var value by remember {
            mutableStateOf("")
        }
        var indicatorValue by remember {
            mutableIntStateOf(0)
        }

        CircularIndicator(
            backgroundIndicatorColor = Color.Gray.copy(alpha = 0.5f),
            foregroundIndicatorColor = Color.Red.copy(alpha = 0.5f),
            indicatorStrokeWidth = 80f,
            indicatorValue = indicatorValue,
            title = "Score",
            suffix = "pts"
        )
        OutlinedTextField(value = value, onValueChange = {
            value = it
            indicatorValue = try {
                value.toInt()
            }catch (e:NumberFormatException){
                0
            }
        })
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen()
}