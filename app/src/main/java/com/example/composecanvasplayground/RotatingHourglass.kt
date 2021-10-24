package com.example.composecanvasplayground

import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun RotatingHourglass(size: Dp) {

    val infiniteAnimation = rememberInfiniteTransition()
    val angle by infiniteAnimation.animateFloat(
        initialValue = 0F,
        targetValue = 720F,
        animationSpec = infiniteRepeatable(
            animation = tween(1500, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Restart
        )
    )

    Canvas(modifier = Modifier.size(size)) {
        val canvasWidth = this.size.width
        val canvasHeight = this.size.height
        val center = this.center

        val drawingHeight = canvasHeight * .75F

        val rectHeight = drawingHeight * .1F
        val rectWidth = canvasWidth / 2F

        val lineHeight = drawingHeight * .8F
        val lineWidth = canvasWidth / 3F

        var yLevel = (canvasHeight - drawingHeight) / 2F

        rotate(degrees = angle) {

            drawRectangle(
                topStart = Offset(x = center.x - rectWidth / 2F, y = yLevel),
                size = Size(width = rectWidth, height = rectHeight)
            )

            yLevel += rectHeight

            val leftLineX = center.x - lineWidth / 2F
            val rightLineX = center.x + lineWidth / 2F

            drawLine(
                start = Offset(x = leftLineX, y = yLevel),
                end = Offset(x = leftLineX, y = yLevel + lineHeight / 4F)
            )
            drawLine(
                start = Offset(x = rightLineX, y = yLevel),
                end = Offset(x = rightLineX, y = yLevel + lineHeight / 4F)
            )

            yLevel += lineHeight / 4F

            drawLine(
                start = Offset(x = leftLineX, y = yLevel),
                end = Offset(x = leftLineX + lineWidth / 3F, y = yLevel + lineHeight / 4F)
            )
            drawLine(
                start = Offset(x = rightLineX, y = yLevel),
                end = Offset(x = rightLineX - lineWidth / 3F, y = yLevel + lineHeight / 4F)
            )

            yLevel += lineHeight / 4F

            drawLine(
                start = Offset(x = leftLineX + lineWidth / 3F, y = yLevel),
                end = Offset(x = leftLineX, y = yLevel + lineHeight / 4F)
            )
            drawLine(
                start = Offset(x = rightLineX - lineWidth / 3F, y = yLevel),
                end = Offset(x = rightLineX, y = yLevel + lineHeight / 4F)
            )

            yLevel += lineHeight / 4F

            drawLine(
                start = Offset(x = leftLineX, y = yLevel),
                end = Offset(x = leftLineX, y = yLevel + lineHeight / 4F)
            )
            drawLine(
                start = Offset(x = rightLineX, y = yLevel),
                end = Offset(x = rightLineX, y = yLevel + lineHeight / 4F)
            )

            yLevel += lineHeight / 4F

            drawRectangle(
                topStart = Offset(x = center.x - rectWidth / 2F, y = yLevel),
                size = Size(width = rectWidth, height = rectHeight)
            )
        }
    }
}

fun DrawScope.drawLine(
    start: Offset,
    end: Offset,
) {
    drawLine(
        color = Color.Cyan,
        start = start,
        end = end,
    )
}

fun DrawScope.drawRectangle(
    topStart: Offset,
    size: Size
) {
    drawRoundRect(
        color = Color.Cyan,
        topLeft = topStart,
        size = size,
        cornerRadius = CornerRadius(50F, 50F),
        style = Stroke(Stroke.HairlineWidth)
    )
}

@Preview
@Composable
fun RotatingDemo() {
    RotatingHourglass(size = 100.dp)
}
