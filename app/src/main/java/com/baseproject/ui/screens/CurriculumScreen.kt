package com.baseproject.ui.screens

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.ProgressIndicatorDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SheetState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.boundsInParent
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.baseproject.R
import com.baseproject.data.modules
import com.baseproject.theme.extraLargeText
import com.baseproject.theme.mediumSpacing
import com.baseproject.theme.smallSpacing
import com.baseproject.theme.smallText
import com.baseproject.theme.smallestText
import com.baseproject.theme.progressColor
import com.baseproject.theme.trackColor
import com.baseproject.theme.trackDisabled
import com.baseproject.ui.modals.AchievementModal

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CurriculumScreen(
    navController: NavController = rememberNavController()
){

    Scaffold(
        topBar = {
            TopAppBar(
                modifier = Modifier.padding(horizontal = smallSpacing),
                title = {},
                navigationIcon = {
                    Icon(
                        modifier = Modifier.clickable{ navController.navigateUp() },
                        painter = painterResource(R.drawable.ic_go_back),
                        contentDescription = null)
                }
            )
        }
    ) { paddingValues ->

        Surface(modifier = Modifier.padding(paddingValues)) {

            val completed = modules.filter { it.state == PathState.COMPLETED }.size
            val total = modules.size

            Column(
                modifier = Modifier.padding(horizontal = mediumSpacing)
                    .fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                ) {
                    Text(
                        text = "Stage $completed of $total",
                        fontSize = smallText,
                        fontWeight = FontWeight.W400,
                        lineHeight = 30.sp,
                        color = Color.DarkGray
                    )
                    Text(
                        text = "Fullstack mobile \nengineer path",
                        fontSize = extraLargeText,
                        fontWeight = FontWeight.W700
                    )
                }

                Spacer(modifier = Modifier.height(mediumSpacing))

                Box {

                    LazyVerticalGrid(
                        modifier = Modifier.fillMaxSize(),
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        columns = GridCells.Fixed(2)
                    ) {
                        itemsIndexed(
                            items = modules,
                            key = { _, module -> module.id }
                        ) { index, module ->

                            val offset = if (module.state != PathState.COMPLETED &&
                                index % 2 == 1) 50.dp else 0.dp

                            ModuleCard(
                                modifier = Modifier
                                    .offset(y = offset),
                                module
                            )
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ModuleCard(
    modifier: Modifier = Modifier,
    module: Module
){

    val modalState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true
    )
    var showModal by remember { mutableStateOf(false) }

    if (showModal && module.state == PathState.COMPLETED){
        Modal(module, modalState) {
            showModal = false
        }
    }


    Column(
        modifier = modifier.padding(mediumSpacing)
            .background(Color.Transparent, shape = RoundedCornerShape(16.dp))
            .clickable{
                showModal = !showModal
            },
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Box(
            contentAlignment = Alignment.Center
        ){
            if (module.state == PathState.CURRENT || module.state == PathState.LOCKED){
                CircularProgressIndicator(
                    progress = { module.progress},
                    modifier = Modifier.size(112.dp),
                    color = progressColor,
                    strokeWidth = 6.dp,
                    trackColor = if (module.state == PathState.CURRENT) trackColor else trackDisabled,
                    strokeCap = ProgressIndicatorDefaults.CircularDeterminateStrokeCap,
                )
            }

            Image(
                modifier = Modifier.size(80.dp),
                painter = painterResource(moduleImage.getValue(module.state)),
                contentDescription = null
            )
        }

        Spacer(modifier = Modifier.height(mediumSpacing))

        Text(
            modifier = Modifier.width(100.dp)
                .align(Alignment.CenterHorizontally),
            text = module.title,
            lineHeight = 15.sp,
            fontSize = smallestText,
            fontWeight = FontWeight.W600,
            textAlign = TextAlign.Center
        )
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Modal(
    module: Module,
    state: SheetState,
    onDismiss: () -> Unit
){
    ModalBottomSheet(
        onDismissRequest = { onDismiss()},
        sheetState = state
    ) {
        AchievementModal(module) {
            onDismiss()
        }
    }

}


val moduleImage = hashMapOf(
    PathState.COMPLETED to R.drawable.ic_badge_unlocked,
    PathState.CURRENT to R.drawable.ic_badge_blue,
    PathState.LOCKED to R.drawable.ic_badge_locked
)

enum class PathState {
    COMPLETED,
    CURRENT,
    LOCKED
}

data class Module(
    val id: Int,
    val title: String,
    val state: PathState,
    val progress: Float
)


@Preview
@Composable
fun CurriculumScreenPreview(){
    CurriculumScreen()
}