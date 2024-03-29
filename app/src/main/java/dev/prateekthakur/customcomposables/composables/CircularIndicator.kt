package dev.prateekthakur.customcomposables.composables

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp

@Composable
fun CircularIndicator(
    canvasSize: Dp = 300.dp,
    indicatorValue: Int = 0,
    maxIndicatorValue: Int = 100,
    backgroundIndicatorColor: Color,
    foregroundIndicatorColor: Color,
    indicatorStrokeWidth: Float,
    title:String = "Value",
    titleColor:Color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f),
    titleFontSize:TextUnit = MaterialTheme.typography.headlineSmall.fontSize,
    suffix:String = "",
    displayColor:Color = MaterialTheme.colorScheme.onSurface,
    displayFontSize:TextUnit = MaterialTheme.typography.headlineLarge.fontSize
) {
    val allowedIndicatorValue = if (indicatorValue<maxIndicatorValue) indicatorValue else maxIndicatorValue
    var animatedIndicatorValue by remember {
        mutableFloatStateOf(allowedIndicatorValue.toFloat())
    }
    LaunchedEffect(key1 = indicatorValue) {
        animatedIndicatorValue = allowedIndicatorValue.toFloat()
    }
    val percentage = ((animatedIndicatorValue / maxIndicatorValue) * 100)
    val sweepAngle by animateFloatAsState(
        targetValue = (2.4 * percentage).toFloat(),
        animationSpec = tween(durationMillis = 200), label = "",
    )
    val animatedDisplayTextColor by animateColorAsState(
        targetValue = if (allowedIndicatorValue == 0)
            MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f)
        else
            displayColor,
        animationSpec = tween(300), label = ""
    )
    val receivedValue by animateIntAsState(
        targetValue = allowedIndicatorValue,
        animationSpec = tween(300), label = ""
    )
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .size(canvasSize)
            .drawBehind {
                val componentSize = size / 1.25f
                backgroundIndicator(
                    componentSize,
                    indicatorColor = backgroundIndicatorColor,
                    indicatorStrokeWidth = indicatorStrokeWidth
                )
                foregroundIndicator(
                    sweepAngle = sweepAngle,
                    indicatorColor = foregroundIndicatorColor,
                    indicatorStrokeWidth = indicatorStrokeWidth,
                    componentSize = componentSize
                )
            }
    ) {
        EmbeddedElements(
            bigText = "$receivedValue$suffix",
            bigTextColor = animatedDisplayTextColor,
            bigTextSize = displayFontSize,
            smallText = title,
            smallTextColor = titleColor,
            smallTextSize = titleFontSize
        )
    }
}

fun DrawScope.backgroundIndicator(
    componentSize: Size,
    indicatorColor: Color,
    indicatorStrokeWidth: Float
) {
    drawArc(
        size = componentSize,
        color = indicatorColor,
        startAngle = 150f,
        sweepAngle = 240f,
        useCenter = false,
        topLeft = Offset(
            x = (size.width - componentSize.width) / 2,
            y = (size.height - componentSize.height) / 2
        ),
        style = Stroke(
            width = indicatorStrokeWidth,
            cap = StrokeCap.Round
        )
    )
}

fun DrawScope.foregroundIndicator(
    sweepAngle: Float,
    componentSize: Size,
    indicatorColor: Color,
    indicatorStrokeWidth: Float
) {
    drawArc(
        size = componentSize,
        color = indicatorColor,
        startAngle = 150f,
        sweepAngle = sweepAngle,
        useCenter = false,
        topLeft = Offset(
            x = (size.width - componentSize.width) / 2,
            y = (size.height - componentSize.height) / 2
        ),
        style = Stroke(
            width = indicatorStrokeWidth,
            cap = StrokeCap.Round
        )
    )
}

@Composable
fun EmbeddedElements(
    bigText:String,
    bigTextColor:Color,
    bigTextSize: TextUnit,
    smallText:String,
    smallTextColor:Color,
    smallTextSize: TextUnit,
){
    Text(text = smallText, style = TextStyle(color = smallTextColor, fontSize = smallTextSize), textAlign = TextAlign.Center)
    Text(text = bigText, style = TextStyle(color = bigTextColor, fontSize = bigTextSize, fontWeight = FontWeight.Bold), textAlign = TextAlign.Center)
}

@Preview(showBackground = true)
@Composable
fun CircularIndicatorPreview() {
    CircularIndicator(
        backgroundIndicatorColor = Color.Gray.copy(alpha = 0.4f),
        indicatorStrokeWidth = 80f,
        indicatorValue = 64,
        maxIndicatorValue = 200,
        foregroundIndicatorColor = Color(50, 100, 200)
    )
}
