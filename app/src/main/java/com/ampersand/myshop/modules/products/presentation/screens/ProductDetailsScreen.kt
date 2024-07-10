package com.ampersand.myshop.modules.products.presentation.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ampersand.myshop.modules.products.domain.model.ProductModel
import com.ampersand.myshop.modules.products.presentation.components.ProductDetails
import com.ampersand.myshop.util.UIcomponents.AppTopbar

data class ProductViewState(
    val product: ProductModel? = null,
)


@Composable
internal fun ProductDetailsScreen ( viewModel: ProductsViewModel) {
    val productViewState = viewModel.productViewState.value
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            AppTopbar(text = "Product Details")
        }
    ) {
        Column (
            modifier = Modifier.padding(top = it.calculateTopPadding(), bottom = it.calculateBottomPadding(), start = 16.dp, end = 16.dp),
        ) {
            if (productViewState != null) {
                if (productViewState.product != null) {
                    ProductDetails(product = productViewState.product)
                } else {
                    Text("Product not found")
                }
            }
        }
    }
}

