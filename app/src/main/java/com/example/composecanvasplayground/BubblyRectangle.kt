package com.example.composecanvasplayground

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun BubblyRectangle(size: Dp) {
    Canvas(modifier = Modifier.fillMaxSize()) {
        val canvasSize = this.size
        val canvasWidth = canvasSize.width
        val canvasHeight = canvasSize.height

//        val path = Path()
//        path.moveTo(100F, 100F)
//        path.lineTo(500F, 100F)
//        path.lineTo(500F, 500F)
//        path.lineTo(100F, 500F)

        val points = listOf(
            Offset(100F, 100F),
            Offset(500F, 100F),
            Offset(500F, 500F),
            Offset(100F, 500F)
        )

        val path = Path().apply {

        }

        // this is not finished

        drawPath(
            path = path,
            color = Color.Cyan,
            style = Stroke(10F)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun BubblyRectangleDemo() {
    BubblyRectangle(size = 150.dp)
}
