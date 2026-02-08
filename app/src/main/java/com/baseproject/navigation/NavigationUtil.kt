package com.baseproject.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.baseproject.ui.screens.CurriculumScreen
import com.baseproject.ui.screens.HomeScreen

object NavigationUtil {

    @Composable
    fun InitNavigation() {

        val navController = rememberNavController()
        NavHost(navController = navController, startDestination = Route.HOME){
            composable(Route.HOME){ HomeScreen(navController = navController) }
            composable(Route.CURRICULUM){ CurriculumScreen(navController) }
        }

    }


}