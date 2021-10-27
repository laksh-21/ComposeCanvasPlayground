package com.example.composecanvasplayground

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.* // ktlint-disable no-wildcard-imports
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.Dp

@Composable
fun DraggableRectangle(size: Dp) {

    var currentRectTopLeftPos: Offset = Offset.Zero
    var changeInPos: Offset = Offset.Zero
    val action: MutableState<Any?> = remember { mutableStateOf(null) }
    var shouldMove = false
    LaunchedEffect(Unit) {
        action.value = Offset.Zero
    }

    Canvas(
        modifier = Modifier
            .fillMaxSize()
            .pointerInput(Unit) {
                detectDragGestures(
                    onDragStart = { offset ->
                        if (isInBounds(currentRectTopLeftPos, size.value, offset)) {
                            shouldMove = true
                        } else {
                            return@detectDragGestures
                        }
                        action.value = offset
                    },
                    onDrag = { _, dragAmount ->
                        if (!shouldMove) return@detectDragGestures
                        action.value = dragAmount
                        changeInPos = dragAmount
                        currentRectTopLeftPos += changeInPos
                    },
                    onDragEnd = {
                        shouldMove = false
                    }
                )
            }
    ) {
        action.value?.let {
            translate(left = changeInPos.x, top = changeInPos.y) {
                drawRect(
                    color = Color.Cyan,
                    topLeft = currentRectTopLeftPos,
                    size = Size(size.value, size.value),
                )
            }
        }
    }
}

private fun isInBounds(rectTopLeft: Offset, rectSize: Float, touchPos: Offset): Boolean {
    val rectTopY = rectTopLeft.y
    val rectBottomY = rectTopLeft.y + rectSize
    val rectStartX = rectTopLeft.x
    val rectEndX = rectTopLeft.x + rectSize

    return (touchPos.x in rectStartX..rectEndX && touchPos.y in rectTopY..rectBottomY)
}
