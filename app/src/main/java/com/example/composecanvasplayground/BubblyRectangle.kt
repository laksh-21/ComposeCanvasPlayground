package com.example.composecanvasplayground

import android.util.Log
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

var waveHorizontalRadius = 75F
var waveVerticalRadius = 75F
var sideWidth = 100F

var waveVerticalTopRadius = 75F
var waveVerticalBottomRadius = 75F

var totalWidth = 800F
var totalHeight = 1200F
var waveCenterY = totalHeight / 2F

private val action: MutableState<Any?> = mutableStateOf(null)

@Composable
fun BubblyRectangle(size: Dp) {
    LaunchedEffect(Unit) {
        action.value = 1
    }
    Canvas(
        modifier = Modifier
            .fillMaxSize()
            .pointerInput(Unit) {
                detectDragGestures(
                    onDragStart = { offset ->
//                        action.value = offset
                    },
                    onDrag = { _, dragAmount ->
                        waveCenterY += dragAmount.y
                        if (dragAmount.y > 0) {
                            // going down
                            if (waveVerticalBottomRadius > waveVerticalRadius) {
                                waveVerticalBottomRadius -= dragAmount.y
                                if (waveVerticalBottomRadius < waveVerticalRadius) waveVerticalBottomRadius = waveVerticalRadius
                            } else {
                                waveVerticalTopRadius += dragAmount.y
                            }
                        } else {
                            // going up
                            if (waveVerticalTopRadius > waveVerticalRadius) {
                                waveVerticalTopRadius -= -dragAmount.y
                                if (waveVerticalTopRadius < waveVerticalRadius) waveVerticalTopRadius = waveVerticalRadius
                            } else {
                                waveVerticalBottomRadius += -dragAmount.y
                            }
                        }
                        action.value = dragAmount
                        Log.d("curvy", "$waveVerticalTopRadius,  $waveVerticalBottomRadius")
                    },
                )
            }
    ) {
        action.value?.let {
            drawPath(
                path = getPath(),
                color = Color.Cyan,
                style = Stroke(10F)
            )
        }
    }
}

fun getPath(): Path {
    val path = Path()
//    waveCenterY = if (waveCenterY == 0f) view.height.toFloat() / 2 else waveCenterY
//    waveCenterY = if (waveCenterY == 0f) totalHeight / 2 else waveCenterY
//    waveHorizontalRadius = getWaveHorRadius(1 - (percent / 100), view)
//    waveVerticalRadius = getWaveVertRadius(1 - (percent / 100), view)
//    sideWidth = getSideWidth(1 - (percent / 100), view)

    path.reset()
//    val maskWidth = view.width - sideWidth
    val maskWidth = totalWidth - sideWidth
    path.moveTo(maskWidth - sideWidth, 0f)
    path.lineTo(0f, 0f)
//    path.lineTo(0f, view.height.toFloat())
    path.lineTo(0f, totalHeight)
//    path.lineTo(maskWidth, view.height.toFloat())
    path.lineTo(maskWidth, totalHeight)

//    if (percent == 100f) {
//        path.close()
//        return path
//    }

    val curveStartY = waveCenterY + waveVerticalBottomRadius
    path.lineTo(maskWidth, curveStartY)
    logThem(maskWidth, curveStartY)
//    Log.d("curves", "horizontal: " + (maskWidth - waveHorizontalRadius))
//    Log.d("curves", "vertical: " + (curveStartY - waveVerticalRadius))
    path.cubicTo(
        maskWidth,
        (curveStartY - waveVerticalBottomRadius * 0.1346194756).toFloat(),
        (maskWidth - waveHorizontalRadius * 0.05341339583).toFloat(),
        (curveStartY - waveVerticalBottomRadius * 0.2412779634).toFloat(),
        (maskWidth - waveHorizontalRadius * 0.1561501458).toFloat(),
        (curveStartY - waveVerticalBottomRadius * 0.3322374268).toFloat()
    )
    logThem(
        (maskWidth - waveHorizontalRadius * 0.1561501458).toFloat(),
        (curveStartY - waveVerticalBottomRadius * 0.3322374268).toFloat()
    )
    path.cubicTo(
        (maskWidth - waveHorizontalRadius * 0.2361659167).toFloat(),
        (curveStartY - waveVerticalBottomRadius * 0.4030805244).toFloat(),
        (maskWidth - waveHorizontalRadius * 0.3305285625).toFloat(),
        (curveStartY - waveVerticalBottomRadius * 0.4561193293).toFloat(),
        (maskWidth - waveHorizontalRadius * 0.5012484792).toFloat(),
        (curveStartY - waveVerticalBottomRadius * 0.5350576951).toFloat()
    )
    logThem(
        (maskWidth - waveHorizontalRadius * 0.5012484792).toFloat(),
        (curveStartY - waveVerticalBottomRadius * 0.5350576951).toFloat()
    )
    path.cubicTo(
        (maskWidth - waveHorizontalRadius * 0.515878125).toFloat(),
        (curveStartY - waveVerticalBottomRadius * 0.5418222317).toFloat(),
        (maskWidth - waveHorizontalRadius * 0.5664134792).toFloat(),
        (curveStartY - waveVerticalBottomRadius * 0.5650349878).toFloat(),
        (maskWidth - waveHorizontalRadius * 0.574934875).toFloat(),
        (curveStartY - waveVerticalBottomRadius * 0.5689655122).toFloat()
    )
    logThem(
        (maskWidth - waveHorizontalRadius * 0.574934875).toFloat(),
        (curveStartY - waveVerticalBottomRadius * 0.5689655122).toFloat()
    )
    path.cubicTo(
        (maskWidth - waveHorizontalRadius * 0.7283715208).toFloat(),
        (curveStartY - waveVerticalBottomRadius * 0.6397387195).toFloat(),
        (maskWidth - waveHorizontalRadius * 0.8086618958).toFloat(),
        (curveStartY - waveVerticalBottomRadius * 0.6833456585).toFloat(),
        (maskWidth - waveHorizontalRadius * 0.8774032292).toFloat(),
        (curveStartY - waveVerticalBottomRadius * 0.7399037439).toFloat()
    )
    logThem(
        (maskWidth - waveHorizontalRadius * 0.8774032292).toFloat(),
        (curveStartY - waveVerticalBottomRadius * 0.7399037439).toFloat()
    )
    path.cubicTo(
        (maskWidth - waveHorizontalRadius * 0.9653464583).toFloat(),
        (curveStartY - waveVerticalBottomRadius * 0.8122605122).toFloat(),
        maskWidth - waveHorizontalRadius,
        (curveStartY - waveVerticalBottomRadius * 0.8936183659).toFloat(),
        maskWidth - waveHorizontalRadius, curveStartY - waveVerticalBottomRadius // this is the far back point of the curve
    )
    logThem(
        maskWidth - waveHorizontalRadius, curveStartY - waveVerticalBottomRadius
    )
    path.cubicTo(
        maskWidth - waveHorizontalRadius,
        (curveStartY - (waveVerticalBottomRadius + waveVerticalTopRadius * 0.100142878)).toFloat(),
        (maskWidth - waveHorizontalRadius * 0.9595746667).toFloat(),
        (curveStartY - (waveVerticalBottomRadius + waveVerticalTopRadius * 0.1887991951)).toFloat(),
        (maskWidth - waveHorizontalRadius * 0.8608411667).toFloat(),
        (curveStartY - (waveVerticalBottomRadius + waveVerticalTopRadius * 0.270484439)).toFloat()
    )
    logThem(
        (maskWidth - waveHorizontalRadius * 0.8608411667).toFloat(),
        (curveStartY - waveVerticalTopRadius * 1.270484439).toFloat()
    )
    path.cubicTo(
        (maskWidth - waveHorizontalRadius * 0.7852123333).toFloat(),
        (curveStartY - (waveVerticalBottomRadius + waveVerticalTopRadius * 0.3330544756)).toFloat(),
        (maskWidth - waveHorizontalRadius * 0.703382125).toFloat(),
        (curveStartY - (waveVerticalBottomRadius + waveVerticalTopRadius * 0.3795848049)).toFloat(),
        (maskWidth - waveHorizontalRadius * 0.5291125625).toFloat(),
        (curveStartY - (waveVerticalBottomRadius + waveVerticalTopRadius * 0.4665102805)).toFloat()
    )
    logThem(
        (maskWidth - waveHorizontalRadius * 0.5291125625).toFloat(),
        (curveStartY - waveVerticalTopRadius * 1.4665102805).toFloat()
    )
    path.cubicTo(
        (maskWidth - waveHorizontalRadius * 0.5241858333).toFloat(),
        (curveStartY - (waveVerticalBottomRadius + waveVerticalTopRadius * 0.4689677195)).toFloat(),
        (maskWidth - waveHorizontalRadius * 0.505739125).toFloat(),
        (curveStartY - (waveVerticalBottomRadius + waveVerticalTopRadius * 0.4781625854)).toFloat(),
        (maskWidth - waveHorizontalRadius * 0.5015305417).toFloat(),
        (curveStartY - (waveVerticalBottomRadius + waveVerticalTopRadius * 0.4802616098)).toFloat()
    )
    logThem(
        (maskWidth - waveHorizontalRadius * 0.5015305417).toFloat(),
        (curveStartY - waveVerticalTopRadius * 1.4802616098).toFloat()
    )
    path.cubicTo(
        (maskWidth - waveHorizontalRadius * 0.3187486042).toFloat(),
        (curveStartY - (waveVerticalBottomRadius + waveVerticalTopRadius * 0.5714239024)).toFloat(),
        (maskWidth - waveHorizontalRadius * 0.2332057083).toFloat(),
        (curveStartY - (waveVerticalBottomRadius + waveVerticalTopRadius * 0.6204116463)).toFloat(),
        (maskWidth - waveHorizontalRadius * 0.1541165417).toFloat(),
        (curveStartY - (waveVerticalBottomRadius + waveVerticalTopRadius * 0.687403)).toFloat()
    )
    logThem(
        (maskWidth - waveHorizontalRadius * 0.1541165417).toFloat(),
        (curveStartY - waveVerticalTopRadius * 1.687403).toFloat()
    )
    path.cubicTo(
        (maskWidth - waveHorizontalRadius * 0.0509933125).toFloat(),
        (curveStartY - (waveVerticalBottomRadius + waveVerticalTopRadius * 0.774752061)).toFloat(),
        maskWidth, (curveStartY - (waveVerticalBottomRadius + waveVerticalTopRadius * 0.8709256829)).toFloat(),
        maskWidth, curveStartY - (waveVerticalTopRadius + waveVerticalBottomRadius)
    )
//    logThem(
//        maskWidth, curveStartY - waveVerticalRadius * 2
//    )
    path.lineTo(maskWidth, 0f)
    path.close()
    return path
}

private fun logThem(pt1: Float, pt2: Float) {
    Log.d("curves", "($pt1, $pt2)")
}

@Preview(showBackground = true)
@Composable
fun BubblyRectangleDemo() {
    BubblyRectangle(size = 150.dp)
}
