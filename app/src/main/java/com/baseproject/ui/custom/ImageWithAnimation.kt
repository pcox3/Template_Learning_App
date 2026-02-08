package com.baseproject.ui.custom

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.painter.Painter



@Composable
fun FlipImage(
    frontPainter: Painter,
    backPainter: Painter,
    modifier: Modifier = Modifier,
    flip: Boolean,
    durationMillis: Int = 600
) {
    val rotation by animateFloatAsState(
        targetValue = if (flip) 180f else 0f,
        animationSpec = tween(
            durationMillis = durationMillis,
            easing = FastOutSlowInEasing
        ), label = "flip"
    )

    Box(
        modifier = modifier
            .graphicsLayer {
                rotationY = rotation
                cameraDistance = 12 * density
            },
        contentAlignment = Alignment.Center
    ) {
        if (rotation <= 90f) {
            Image(
                painter = frontPainter,
                contentDescription = null,
                modifier = Modifier.fillMaxSize()
            )
        } else {
            Image(
                painter = backPainter,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxSize()
                    .graphicsLayer {
                        rotationY = 180f
                    }
            )
        }
    }
}


@Composable
fun InfiniteRotatingImage(
    visible: Boolean,
    painter: Painter,
    modifier: Modifier = Modifier,
    durationMillis: Int = 3000
) {
    AnimatedVisibility(visible = visible) {

        val infiniteTransition = rememberInfiniteTransition(label = "rotation")

        val rotation by infiniteTransition.animateFloat(
            initialValue = 0f,
            targetValue = 360f,
            animationSpec = infiniteRepeatable(
                animation = tween(
                    durationMillis = durationMillis,
                    easing = LinearEasing
                )
            ), label = "rotationAnim"
        )

        Image(
            painter = painter,
            contentDescription = null,
            modifier = modifier.graphicsLayer {
                rotationZ = rotation
            }
        )
    }
}
