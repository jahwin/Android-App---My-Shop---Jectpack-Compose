package com.ampersand.myshop.modules.products.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ampersand.myshop.modules.products.domain.model.ProductModel


@Composable
fun ProductDetails(
    product: ProductModel
) {
        Column(
            modifier = Modifier.padding( start = 16.dp, end = 16.dp),
        ){
            Text(text = product.name, style = MaterialTheme.typography.bodyLarge)
            product.data?.forEach { (key, value) ->
                Text(
                    text = "$key: $value",
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }

}