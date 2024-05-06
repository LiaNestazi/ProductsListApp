package com.productslistapp.presentation.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.productslistapp.presentation.MainViewModel
import com.productslistapp.presentation.ui.composables.pages.ProductDetails
import com.productslistapp.presentation.ui.composables.pages.ProductList

@Composable
fun SetupNavGraph(
    navController: NavHostController,
    mainViewModel: MainViewModel
) {

    NavHost(navController = navController, startDestination = "ProductList") {
        composable("ProductList") {
            ProductList(navController = navController, vm = mainViewModel)
        }
        composable(
            route = "ProductInfo/{id}",
            arguments = listOf(
                navArgument("id"){
                    type = NavType.IntType
                },
            )
        ){
            ProductDetails(
                navController = navController,
                mainViewModel = mainViewModel,
                itemId = it.arguments?.getInt("id")

            )
        }
    }
}