package com.jeffrwatts.naturalistguide

import androidx.navigation.NavController

object NaturalistGuideDestinations {
    const val HAWAII_SEA_LIFE = "hawaiiSeaLife"
    const val HAWAII_BIRDS = "hawaiiBirds"
}

class NaturalistGuideActions(navController: NavController) {
    val navigateToHawaiiSeaLife: (currentRoute: String?) -> Unit = { currentRoute->
        if (currentRoute != NaturalistGuideDestinations.HAWAII_SEA_LIFE) {
            navController.navigate(NaturalistGuideDestinations.HAWAII_SEA_LIFE) {
                popUpTo(navController.graph.startDestinationId)
                launchSingleTop = true
            }
        }
    }
    val navigateToHawaiiBirds: (currentRoute: String?) -> Unit = { currentRoute->
        if (currentRoute != NaturalistGuideDestinations.HAWAII_BIRDS) {
            navController.navigate(NaturalistGuideDestinations.HAWAII_BIRDS) {
                popUpTo(navController.graph.startDestinationId)
                launchSingleTop = true
            }
        }
    }
}