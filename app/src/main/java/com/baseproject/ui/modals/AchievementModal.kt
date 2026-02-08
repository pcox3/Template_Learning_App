package com.baseproject.ui.modals

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.baseproject.R
import com.baseproject.theme.extraLargeSpacing
import com.baseproject.theme.largeSpacing
import com.baseproject.theme.mediumSpacing
import com.baseproject.ui.custom.FlipImage
import com.baseproject.ui.custom.InfiniteRotatingImage
import com.baseproject.ui.screens.Module


@Composable
fun AchievementModal(
    module: Module,
    onShare: () -> Unit = {}
) {
    var flipped by remember { mutableStateOf(false) }
    var show by remember { mutableStateOf(true) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White, shape = RoundedCornerShape(24.dp))
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier = Modifier.height(mediumSpacing))

        OutlinedButton(
            onClick = {
                flipped = !flipped
            },
            shape = RoundedCornerShape(50),
            border = BorderStroke(1.dp, Color(0xFFE2E6F0)),
            colors = ButtonDefaults.outlinedButtonColors(
                contentColor = Color(0xFF1A1A1A)
            )
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_refresh),
                contentDescription = null,
                modifier = Modifier.size(mediumSpacing)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text("Flip badge")
        }

        Spacer(modifier = Modifier.height(largeSpacing))

        Box(
            modifier = Modifier.height(300.dp),
            contentAlignment = Alignment.Center
        ) {

            InfiniteRotatingImage(
                visible = show,
                painter = painterResource(R.drawable.ic_badge_filter),
                modifier = Modifier.width(279.dp)
            )

            FlipImage(
                frontPainter = painterResource(R.drawable.ic_badge_unlocked),
                backPainter = painterResource(R.drawable.ic_badge_red),
                flip = flipped,
                modifier = Modifier
                    .width(236.dp)
                    .clickable { flipped = !flipped }
            )
        }

        Spacer(modifier = Modifier.height(mediumSpacing))

        Text(
            text = module.title,
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            fontSize = 28.sp,
            textAlign = TextAlign.Center,
            color = Color(0xFF0F172A)
        )

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = "Congratulations, you've earned a badge for completing this module!",
            style = MaterialTheme.typography.bodyMedium,
            textAlign = TextAlign.Center,
            color = Color(0xFF64748B)
        )

        Spacer(modifier = Modifier.height(extraLargeSpacing))

        Button(
            onClick = onShare,
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF7C3AED)
            )
        ) {
            Text(
                text = "Share your achievement",
                color = Color.White,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Medium
            )
        }

        Spacer(modifier = Modifier.height(24.dp))
    }
}


@Preview
@Composable
fun AchievementModalPreview(){
   // AchievementModal()
}