package com.antoninovitale.beerpedia.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

private const val DETAILS_NAV_ARGUMENT = "beerId"
private const val DETAILS_NAV_ARGUMENT_NA = -1

/**
 * This function uses a [NavHost] to navigate to a start destination and handle other routes.
 * This is the entry point to include as content in the [com.antoninovitale.beerpedia.MainActivity].
 */
@Composable
fun MainNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = Route.BEERS.screen
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        composable(
            route = Route.BEERS.screen
        ) {
            BeerListScreen(
                onNavigateToDetails = { beerId ->
                    navController.navigate("${Route.DETAILS.screen}/$beerId")
                },
            )
        }
        composable(
            route = "${Route.DETAILS.screen}/{$DETAILS_NAV_ARGUMENT}",
            arguments = listOf(navArgument(DETAILS_NAV_ARGUMENT) { type = NavType.IntType })
        ) { backStackEntry ->
            BeerDetailsScreen(beerId = getBeerId(backStackEntry))
        }
    }
}

@Composable
private fun getBeerId(backStackEntry: NavBackStackEntry) =
    backStackEntry.arguments?.getInt(DETAILS_NAV_ARGUMENT) ?: DETAILS_NAV_ARGUMENT_NA
