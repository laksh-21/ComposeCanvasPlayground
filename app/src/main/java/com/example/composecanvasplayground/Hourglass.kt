package com.example.composecanvasplayground

import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun DrawHourglass(size: Dp) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        val allowedSize = size.times(.75F)
        DrawRectangle(allowedSize.times(.10F))
        DrawLines(allowedSize.times(.80F))
        DrawRectangle(allowedSize.times(.10F))
    }
}

@Composable
fun DrawRectangle(height: Dp) {

//    val infiniteTransition = rememberInfiniteTransition()
//    val angle by infiniteTransition.animateFloat(
//        initialValue = 0F,
//        targetValue = 360F,
//        animationSpec = infiniteRepeatable(
//            animation = tween(1000, easing = LinearEasing),
//            repeatMode = RepeatMode.Restart
//        )
//    )

    Canvas(
        modifier = Modifier
            .fillMaxWidth(.75F)
            .height(height = height)
    ) {
        val canvasSize = size
        val canvasWidth = canvasSize.width
        val canvasHeight = canvasSize.height

        drawRoundRect(
            color = Color.Cyan,
            size = Size(
                width = canvasWidth / 2F,
                height = canvasHeight
            ),
            topLeft = Offset(
                x = canvasWidth / 4F,
                y = center.y - canvasHeight / 2F,
            ),
            style = Stroke(width = 10F),
            cornerRadius = CornerRadius(x = 50F, y = 50F)
        )
    }
}

@Composable
fun DrawLines(height: Dp) {
    Canvas(
        modifier = Modifier
            .fillMaxWidth(.75F)
            .height(height = height)
    ) {
        val canvasSize = size

        val canvasWidth = canvasSize.width
        val canvasHeight = canvasSize.height

        val canvasCenter = center

        val leftPoint1 = Offset(
            x = canvasWidth / 3F,
            y = center.y - canvasHeight / 2F,
        )
        val leftPoint2 = Offset(
            x = canvasWidth / 3F,
            y = center.y - canvasHeight / 3F,
        )

        val leftPoint3 = Offset(
            x = canvasWidth / 2.5F,
            y = center.y,
        )

        val leftPoint4 = Offset(
            x = (canvasWidth / 3F),
            y = center.y + canvasHeight / 3F,
        )
        val leftPoint5 = Offset(
            x = (canvasWidth / 3F),
            y = center.y + canvasHeight / 2F,
        )

        val leftPoints = listOf(
            leftPoint1,
            leftPoint2,
            leftPoint3,
            leftPoint4,
            leftPoint5,
        )

        val rightPoint1 = Offset(
            x = 2 * (canvasWidth / 3F),
            y = center.y - canvasHeight / 2F,
        )
        val rightPoint2 = Offset(
            x = 2 * (canvasWidth / 3F),
            y = center.y - canvasHeight / 3F,
        )

        val rightPoint3 = Offset(
            x = 3 * (canvasWidth / 5F),
            y = center.y,
        )

        val rightPoint4 = Offset(
            x = 2 * (canvasWidth / 3F),
            y = center.y + canvasHeight / 3F,
        )
        val rightPoint5 = Offset(
            x = 2 * (canvasWidth / 3F),
            y = center.y + canvasHeight / 2F,
        )

        val rightPoints = listOf(
            rightPoint1,
            rightPoint2,
            rightPoint3,
            rightPoint4,
            rightPoint5,
        )

        val leftPath = Path()
        leftPath.moveTo(leftPoint1)
        for (point in leftPoints) {
            leftPath.lineTo(point)
        }
        drawPath(
            path = leftPath,
            color = Color.Cyan,
            style = Stroke(10F)
        )

        val rightPath = Path()
        rightPath.moveTo(rightPoint1)
        for (point in rightPoints) {
            rightPath.lineTo(point)
        }
        drawPath(
            path = rightPath,
            color = Color.Cyan,
            style = Stroke(10F)
        )
    }
}

private fun Path.moveTo(offset: Offset) {
    moveTo(x = offset.x, y = offset.y)
}

private fun Path.lineTo(offset: Offset) {
    lineTo(x = offset.x, y = offset.y)
}

@Preview(showBackground = true)
@Composable
fun Demo() {
    DrawHourglass(size = 500.dp)
}
