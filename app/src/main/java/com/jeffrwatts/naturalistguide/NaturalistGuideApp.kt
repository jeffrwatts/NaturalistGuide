package com.jeffrwatts.naturalistguide

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.jeffrwatts.naturalistguide.ui.theme.NaturalistGuideTheme

@Composable
fun NaturalistGuideApp() {
    NaturalistGuideTheme {
        val navController = rememberNavController()
        val navigationActions = remember(navController) {
            NaturalistGuideActions(navController)
        }

        Scaffold(
            bottomBar = {
                NavigationBar {
                    val navBackStackEntry by navController.currentBackStackEntryAsState()
                    val currentRoute = navBackStackEntry?.destination?.route
                    NavigationBarItem(
                        label = { Text(stringResource(id = R.string.sea_life)) },
                        icon = { Icon(Icons.Filled.Star, null) },
                        selected = currentRoute == NaturalistGuideDestinations.HAWAII_SEA_LIFE,
                        onClick = { navigationActions.navigateToHawaiiSeaLife(currentRoute) }
                    )
                    NavigationBarItem(
                        label = { Text(stringResource(id = R.string.birds)) },
                        icon = { Icon(Icons.Filled.Favorite, null) },
                        selected = currentRoute == NaturalistGuideDestinations.HAWAII_BIRDS,
                        onClick = { navigationActions.navigateToHawaiiBirds(currentRoute) }
                    )
                }
            }
        ) { innerPadding ->
            NatrualistGuideNavGraph(navController = navController, modifier = Modifier.padding(innerPadding))
        }
    }

}