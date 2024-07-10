package com.ampersand.myshop.modules.products.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.ampersand.myshop.Route
import com.ampersand.myshop.modules.products.domain.model.ProductModel
import com.ampersand.myshop.modules.products.presentation.components.ProductDetails
import com.ampersand.myshop.modules.products.presentation.components.ProductItem
import com.ampersand.myshop.ui.theme.WindowInfo
import com.ampersand.myshop.ui.theme.rememberWindowInfo
import com.ampersand.myshop.util.UIcomponents.AppTopbar
import com.ampersand.myshop.util.UIcomponents.LoadingDialog
import kotlinx.coroutines.flow.MutableStateFlow

data class ProductsViewState(
    val isLoading: Boolean = false,
    val products: List<ProductModel> = emptyList(),
    val error: String? = ""
)


@Composable
internal fun ProductsScreen(
    viewModel: ProductsViewModel,
    navController: NavController,
) {
    val productsViewState  by viewModel.productsViewState.collectAsStateWithLifecycle()
    ProductItems(state = productsViewState,navController, viewModel)
}

@Composable
fun ProductItems(
    state: ProductsViewState,
    navController: NavController,
    viewModel:ProductsViewModel
) {
    val windowState = rememberWindowInfo();
    var productViewState by remember { mutableStateOf(ProductViewState()) }

   LoadingDialog(isLoading = state.isLoading)
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            AppTopbar(text ="Products")
        }
    ) {
        if(windowState.screenWidthInfo == WindowInfo.WindowType.Small) {
            LazyVerticalStaggeredGrid(
                columns = StaggeredGridCells.Fixed(1),
                modifier = Modifier.padding(
                    top = it.calculateTopPadding(),
                    bottom = it.calculateBottomPadding(),
                    start = 16.dp,
                    end = 16.dp
                ),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalItemSpacing = 5.dp
            ) {

                items(state.products) { product ->
                    ProductItem(
                        product = product,
                        onClick = {
                            viewModel.productViewState.value = ProductViewState(product)
                            navController.navigate(route = Route.ProductDetailScreen.name)
                        }
                    )
                }
            }
        }else{
            Box( modifier = Modifier
                .padding(
                    top = it.calculateTopPadding(),
                    bottom = it.calculateBottomPadding(),
                    start = it.calculateLeftPadding(layoutDirection = LayoutDirection.Ltr),
                    end = it.calculateRightPadding(layoutDirection = LayoutDirection.Ltr)
                )
                .fillMaxSize(),) {
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {

                    Box(
                        modifier = Modifier.weight(0.4f)) {
                        LazyVerticalStaggeredGrid(
                            columns = StaggeredGridCells.Fixed(1),
                            modifier = Modifier.padding(
                                start = 16.dp,
                                end = 16.dp
                            ),
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            verticalItemSpacing = 5.dp
                        ) {

                            items(state.products) { product ->
                                ProductItem(
                                    product = product,
                                    onClick = {
                                        productViewState = ProductViewState(product)
                                    }
                                )
                            }
                        }
                    }

                    Box(modifier = Modifier.weight(0.6f).fillMaxSize().padding(4.dp)) {
                        if (productViewState.product != null) {
                            ProductDetails(product = productViewState.product!!)
                        } else {
                            Text(
                                "Product not Selected",
                                style = MaterialTheme.typography.bodyMedium,
                                color = Color.White
                            )
                        }

                    }


                }
            }
        }
    }
}