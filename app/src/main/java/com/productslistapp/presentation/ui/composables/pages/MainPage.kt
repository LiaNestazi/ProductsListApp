package com.productslistapp.presentation.ui.composables.pages

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.productslistapp.presentation.MainViewModel
import com.productslistapp.presentation.ui.navigation.SetupNavGraph


@Composable
fun MainPage(mainViewModel: MainViewModel) {
    val navController = rememberNavController()
    Box {
        SetupNavGraph(navController = navController, mainViewModel)
        navController.navigate("ProductList", navOptions = null)
    }
}