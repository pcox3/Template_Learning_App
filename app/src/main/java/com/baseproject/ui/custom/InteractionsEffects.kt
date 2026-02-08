package com.baseproject.ui.custom

import android.media.AudioManager
import android.media.ToneGenerator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalHapticFeedback


enum class StateEffect { ENABLED, DISABLED }

/**
 * Can be RippleEffect, StateEffect
 */
@Composable
fun VisualEffect(){

}


@Composable
fun HapticEffect(
    state: StateEffect,
    onTrigger: () -> Unit
) {
    val haptic = LocalHapticFeedback.current

    LaunchedEffect(state) {
        if (state == StateEffect.ENABLED) {
            haptic.performHapticFeedback(HapticFeedbackType.LongPress)
            onTrigger()
        }
    }
}

@Composable
fun SoundEffect(
    state: StateEffect,
    onTrigger: () -> Unit
) {
    val context = LocalContext.current

    LaunchedEffect(state) {
        if (state == StateEffect.ENABLED) {
            val tone = ToneGenerator(AudioManager.STREAM_NOTIFICATION, 100)
            tone.startTone(ToneGenerator.TONE_PROP_BEEP, 150)
            onTrigger()
        }
    }
}