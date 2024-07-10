package com.ampersand.myshop

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.ampersand.myshop.modules.products.presentation.screens.ProductDetailsScreen
import com.ampersand.myshop.modules.products.presentation.screens.ProductsScreen
import com.ampersand.myshop.modules.products.presentation.screens.ProductsViewModel

sealed class Route(
    val name: String,
    val path: String
) {
    data object ProductsScreen : Route(name="products_screen", path = "/products")
    data object ProductDetailScreen : Route(name="product_detail_screen", path = "/product_detail")
}

@Composable
fun NavGraph(
    startDestination: String,
    productsViewModel: ProductsViewModel = hiltViewModel()
){
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = startDestination
    ){
        navigation(
            route = Route.ProductsScreen.name,
            startDestination = Route.ProductsScreen.path
        ){
            composable(
                route = Route.ProductsScreen.path
            ){
                ProductsScreen(viewModel=productsViewModel, navController = navController,);
            }
        }

        navigation(
            route = Route.ProductDetailScreen.name,
            startDestination =  Route.ProductDetailScreen.path
        ){
            composable(
                route = Route.ProductDetailScreen.path
            ){
                ProductDetailsScreen(viewModel=productsViewModel);
            }
        }

    }
}
