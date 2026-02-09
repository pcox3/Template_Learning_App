package com.baseproject.ui.screens

import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.overscroll
import androidx.compose.foundation.rememberOverscrollEffect
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ProgressIndicatorDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.baseproject.R
import com.baseproject.data.modules
import com.baseproject.data.nextModule
import com.baseproject.navigation.Route
import com.baseproject.theme.colorWhite
import com.baseproject.theme.grey
import com.baseproject.theme.largeSpacing
import com.baseproject.theme.mediumRadius
import com.baseproject.theme.mediumSpacing
import com.baseproject.theme.mediumText
import com.baseproject.theme.progressColor
import com.baseproject.theme.smallRadius
import com.baseproject.theme.smallSpacing
import com.baseproject.theme.smallText
import com.baseproject.theme.smallestSpacing
import com.baseproject.theme.smallestText
import com.baseproject.theme.trackColor
import com.baseproject.theme.trackDisabled
import com.utility.greet


@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navController: NavController = rememberNavController()
){

    val scrollState = rememberScrollState()

   Column(
       modifier = Modifier
           .fillMaxSize()
           .background(colorWhite)
           .verticalScroll(scrollState)
           .overscroll(rememberOverscrollEffect())
   ) {

       Box {
           Image(
               modifier = Modifier.fillMaxSize(),
               contentScale = ContentScale.Crop,
               painter = painterResource(R.drawable.bg), contentDescription = null
           )

           HeaderSection()
       }

       Column(
           modifier = modifier
               .fillMaxWidth()
               .wrapContentHeight()
               .offset(y = (-90).dp)
       ) {

           TodayTaskCard(navController = navController)

           ActiveLearningPath(navController)

           BadgesSection()
       }

   }

}

@Composable
private fun HeaderSection() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp)
    ) {

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Box(
                modifier = Modifier
                    .size(40.dp)
                    .background(Color(0xFFDDE7FF), CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Text("TA", fontWeight = FontWeight.W500)
            }

            Spacer(modifier = Modifier.weight(1f))

            Surface(
                border = BorderStroke(1.dp, grey),
                shape = RoundedCornerShape(50),
                color = Color.Transparent
            ) {
                Row(
                    modifier = Modifier.padding(horizontal = 14.dp, vertical = 6.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(painterResource(R.drawable.ic_streak),
                        tint = Color.Unspecified,
                        contentDescription = null)
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = "3 days",
                        fontSize = smallText,
                        fontWeight = FontWeight.W600
                    )
                }
            }

            Spacer(modifier = Modifier.weight(1f))

            Icon(
                painter = painterResource(R.drawable.ic_chat),
                contentDescription = null
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

        Image(
            painter = painterResource(id = R.drawable.ic_mascot),
            contentDescription = null,
            modifier = Modifier
                .size(180.dp)
                .align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "${greet()} Chidi!",
            style = MaterialTheme.typography.headlineMedium,
            fontFamily = MaterialTheme.typography.headlineMedium.fontFamily,
            fontWeight = FontWeight.W700,
            fontSize = 28.sp,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = "You’re closer than you think 💪🏽",
            color = Color.DarkGray,
            fontSize = smallText,
            fontFamily = MaterialTheme.typography.headlineSmall.fontFamily,
            fontWeight = FontWeight.W400,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.height(24.dp))
    }
}

@Composable
private fun TodayTaskCard(modifier: Modifier = Modifier, navController: NavController) {

    Column(
        modifier = modifier.padding(horizontal = 20.dp, vertical = smallSpacing)
            .clickable{
                navController.navigate(Route.CURRICULUM)
            }
            .shadow(
                elevation = 12.dp,
                shape = RoundedCornerShape(8.dp),
                ambientColor = grey,
                spotColor = grey
            )
    ) {

        Column(
            modifier = modifier.height(112.dp)
                .background(colorWhite)
                .padding(
                    top = 6.dp,
                    start = largeSpacing,
                    end = largeSpacing,
                    bottom = largeSpacing
                ),
            verticalArrangement = Arrangement.Center
        ) {

            Text(
                text = "For today",
                fontSize = smallestText,
                fontFamily = MaterialTheme.typography.headlineMedium.fontFamily,
                fontWeight = FontWeight.Bold
            )

            Spacer(Modifier.height(smallestSpacing))

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {

                Box(
                    contentAlignment = Alignment.Center,
                ) {
                    CircularProgressIndicator(
                        progress = { modules.filter { it.state == PathState.COMPLETED }
                            .size.toFloat() / modules.size.toFloat()},
                        modifier = Modifier.size(48.dp),
                        color = progressColor,
                        strokeWidth = 3.dp,
                        trackColor = if (nextModule()?.state == PathState.CURRENT) trackColor else trackDisabled,
                        strokeCap = ProgressIndicatorDefaults.CircularDeterminateStrokeCap,
                    )
                    Image(
                        painter = painterResource(id = R.drawable.ic_badge_locked),
                        contentDescription = null,
                        modifier = Modifier.size(29.dp)
                    )
                }

                Spacer(modifier = Modifier.width(12.dp))

                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = "Fullstack mobile engineer path",
                        fontSize = smallText,
                        fontFamily = MaterialTheme.typography.labelMedium.fontFamily,
                        fontWeight = FontWeight.Bold
                    )
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            painterResource(R.drawable.ic_curriculum), contentDescription = null,
                            tint = Color.Unspecified
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = "View all modules",
                            color = Color.Gray,
                            lineHeight = 20.sp,
                            fontFamily = MaterialTheme.typography.labelSmall.fontFamily,
                            fontWeight = FontWeight.W400,
                            fontSize = smallText
                        )
                    }
                }

                Icon(painterResource(R.drawable.ic_forward_arrow), contentDescription = null)
            }
        }
    }

    Spacer(modifier = Modifier.height(28.dp))
}

@Composable
private fun ActiveLearningPath(
    navController: NavController = rememberNavController()
) {

    val completed = modules.filter { it.state == PathState.COMPLETED }.size
    val total = modules.size

    Column(
        modifier = Modifier.padding(horizontal = 20.dp)
    ) {

        Text(
            text = "Active learning path",
            fontWeight = FontWeight.W700,
            fontFamily = MaterialTheme.typography.headlineLarge.fontFamily,
            fontSize = mediumText
        )

        Spacer(modifier = Modifier.height(mediumSpacing))

        Column(modifier = Modifier.padding(16.dp)) {

            Text(
                text = nextModule()?.title?:"",
                fontWeight = FontWeight.Bold,
                fontFamily = MaterialTheme.typography.labelMedium.fontFamily,
                fontSize = smallText
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Stage $completed of $total",
                    lineHeight = TextUnit(20f, TextUnitType.Sp),
                    color = Color.Gray,
                    fontWeight = FontWeight.W400,
                    fontFamily = MaterialTheme.typography.bodySmall.fontFamily,
                    fontSize = smallestText
                )
                Spacer(Modifier.width(smallestSpacing))
                LinearProgressIndicator(
                    progress = {nextModule()?.progress?:0f},
                    modifier = Modifier.fillMaxWidth(0.35f),
                    color = Color(0xFF7C3AED),
                    trackColor = Color(0xFFE9D5FF),
                )
            }

            Spacer(modifier = Modifier.height(mediumSpacing))

            Surface(
                modifier = Modifier.fillMaxWidth()
                    .height(72.dp),
                border = BorderStroke(1.dp, Color(0xFFF0F2F5)),
                shape = RoundedCornerShape(smallRadius),
                color = Color.Unspecified
            ) {
                Row(
                    modifier = Modifier.padding(12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Image(
                        painter = painterResource(id = R.drawable.ic_badge_blue),
                        contentDescription = null,
                        modifier = Modifier.size(40.dp)
                    )

                    Spacer(modifier = Modifier.width(12.dp))

                    Column {
                        Text(
                            text = "Learn React",
                            fontSize = smallText,
                            fontFamily = MaterialTheme.typography.labelMedium.fontFamily,
                            lineHeight = TextUnit(1f, TextUnitType.Sp),
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "Component lifecycle",
                            color = Color.DarkGray,
                            fontFamily = MaterialTheme.typography.bodySmall.fontFamily,
                            fontSize = smallestText
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    navController.navigate(Route.CURRICULUM)
                },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(mediumRadius),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF7C3AED)
                )
            ) {
                Row(
                    modifier = Modifier.padding(vertical = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Text(
                        text = "View full path",
                        color = colorWhite,
                        fontSize = smallText,
                        fontWeight = FontWeight.W500
                    )
                    Spacer(modifier = Modifier.width(smallSpacing))
                    Icon(
                        painter = painterResource(R.drawable.ic_right_arrow),
                        contentDescription = null,
                        tint = Color.Unspecified
                    )
                }
            }
        }
    }

    Spacer(modifier = Modifier.height(32.dp))
}

@Composable
private fun BadgesSection() {

    val badges = listOf(
        R.drawable.ic_badge_blue,
        R.drawable.ic_badge_red,
        R.drawable.ic_badge_unlocked
    )

    Column(
        modifier = Modifier.padding(horizontal = 20.dp)
    ) {
        Text(
            text = "Badges",
            fontSize = mediumText,
            fontWeight = FontWeight.W700
        )

        Spacer(modifier = Modifier.height(16.dp))

        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            items(badges, key = {it}){ badge ->
                BadgeItem(badge)
            }
        }
    }
}

@Composable
private fun BadgeItem(@DrawableRes badge: Int) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {

        Image(
            painter = painterResource(id = badge),
            contentDescription = null,
            modifier = Modifier.size(80.dp)
        )

        Spacer(modifier = Modifier.height(9.dp))

        Text(
            text = "Genius",
            fontSize = smallestText,
            fontFamily = MaterialTheme.typography.headlineMedium.fontFamily,
            lineHeight = TextUnit(1f, TextUnitType.Sp),
            fontWeight = FontWeight.Medium
        )

        Text(
            "3/3 perfect scores",
            fontSize = 10.sp,
            color = Color.DarkGray
        )
    }
}


@Preview
@Composable
fun HomeScreenPreview(){
    HomeScreen()
}