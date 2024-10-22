package com.jeffrwatts.naturalistguide

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.jeffrwatts.naturalistguide.ui.birds.BirdScreen
import com.jeffrwatts.naturalistguide.ui.sealife.SeaLifeScreen

@Composable
fun NatrualistGuideNavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = NaturalistGuideDestinations.HAWAII_SEA_LIFE,
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        composable(route = NaturalistGuideDestinations.HAWAII_SEA_LIFE) {
            SeaLifeScreen()
        }
        composable(route = NaturalistGuideDestinations.HAWAII_BIRDS) {
            BirdScreen()
        }
    }
}