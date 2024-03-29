package dev.prateekthakur.customcomposables

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import dev.prateekthakur.customcomposables.composables.ExpandableCard
import dev.prateekthakur.customcomposables.composablesclass.GradientButton
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
    CustomComposablesTheme {
        Greeting("Android")
    }
}

@Preview(showBackground = true)
@Composable
fun MainPreview(){
    Column {
        ExpandableCard(title = "Hello", content = "Hello")
        GradientButton(text = "Click", textColor = Color.White, gradient = Brush.linearGradient(colors = listOf(
            Color.Blue,
            Color.Red))){

        }}
}
