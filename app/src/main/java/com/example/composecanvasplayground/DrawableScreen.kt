package com.example.composecanvasplayground

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

private val action: MutableState<Any?> = mutableStateOf(null)
private val path: Path = Path()
private var startingPoint: Offset = Offset.Zero

@Composable
fun DrawableScreen(size: Dp) {
    Canvas(
        modifier = Modifier
            .fillMaxSize()
            .pointerInput(Unit) {
                detectDragGestures(
                    onDragStart = { offset ->
                        action.value = offset
                        startingPoint = offset
                        path.moveTo(offset)
                    },
                    onDrag = { _, dragAmount: Offset ->
                        action.value = dragAmount
                        path.lineTo(startingPoint + dragAmount)
                        startingPoint += dragAmount
                    }
                )
            },
    ) {
        action.value?.let {
            drawPath(
                path = path,
                color = Color.Cyan,
                style = Stroke(4F)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BubblyRectDemo() {
    DrawableScreen(200.dp)
}
