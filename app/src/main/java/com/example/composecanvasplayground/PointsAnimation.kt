package com.example.composecanvasplayground

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PointMode
import com.example.composecanvasplayground.Point.Companion.distanceOf
import kotlin.math.pow
import kotlin.math.sqrt

const val pointCount = 30
const val maxSpeed = 5
val pointsState: PointsState = PointsState()
const val lineDistThreshold = 300F
const val maxThickness = 8F

@Composable
fun PointsAnimation() {
    Canvas(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Black)
    ) {
        pointsState.points.value.let { points ->
            if (points.isEmpty()) {
                pointsState.generatePoints(size.width, size.height, maxSpeed, pointCount)
            } else {
                drawPoints(
                    points = points.map {
                        it.position
                    },
                    pointMode = PointMode.Points,
                    color = Color.Cyan,
                    strokeWidth = 10F
                )

                points.nestedForEach { point1, point2 ->
                    val dist = point1 distanceOf point2
                    if (dist <= lineDistThreshold) {
                        drawLine(
                            color = Color.Cyan,
                            start = point1.position,
                            end = point2.position,
                            strokeWidth = ((lineDistThreshold - dist) / lineDistThreshold) * maxThickness
                        )
                    }
                }

                pointsState.nextFrame()
            }
        }
    }
}

data class Point(
    var position: Offset,
    val velocity: Offset
) {
    companion object {
        infix fun Point.distanceOf(other: Point): Float {
            return sqrt(
                (this.position.x - other.position.x).pow(2) +
                    (this.position.y - other.position.y).pow(2)
            )
        }
    }
}

class PointsState {
    var points: MutableState<MutableList<Point>> = mutableStateOf(emptyArray<Point>().toMutableList())
    private var width: Float = 0F
    private var height: Float = 0F
    private var pointCount: Int = 0

    fun generatePoints(width: Float, height: Float, maxSpeed: Int, pointCount: Int) {
        this.width = width
        this.height = height
        this.pointCount = pointCount
        val points = mutableListOf<Point>()
        repeat(pointCount) {
            points.add(
                Point(
                    position = Offset(
                        x = (0..width.toInt()).random().toFloat(),
                        y = (0..height.toInt()).random().toFloat()
                    ),
                    velocity = Offset(
                        x = (-maxSpeed..maxSpeed).random().toFloat(),
                        y = (-maxSpeed..maxSpeed).random().toFloat()
                    )
                )
            )
        }
        this.points.value = points
    }

    fun nextFrame() {
        val newPoints = points.value.map {
            getReflectedPoint(Point(it.position + it.velocity, it.velocity), width, height)
        }.toMutableList()
        points.value = newPoints
    }

    private fun getReflectedPoint(point: Point, width: Float, height: Float): Point {
        val pos: Offset = point.position
        var newXVel: Float = point.velocity.x
        var newYVel: Float = point.velocity.y
        if (pos.x in 0F..width && pos.y in 0F..height) {
            return point
        }

        if (pos.x <= 0F || pos.x >= width) {
            newXVel *= -1
        }

        if (pos.y <= 0F || pos.y >= height) {
            newYVel *= -1
        }

        return Point(pos, Offset(newXVel, newYVel))
    }
}

private fun <T> List<T>.nestedForEach(block: (T, T) -> Unit) {
    for (i in this.indices) {
        for (j in i + 1 until this.size) {
            block(this[i], this[j])
        }
    }
}
