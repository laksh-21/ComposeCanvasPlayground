package com.example.composecanvasplayground

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.ui.unit.dp
import com.example.composecanvasplayground.ui.theme.ComposeCanvasPlaygroundTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeCanvasPlaygroundTheme {
                RotatingHourglass(size = 150.dp)
            }
        }
    }
}
