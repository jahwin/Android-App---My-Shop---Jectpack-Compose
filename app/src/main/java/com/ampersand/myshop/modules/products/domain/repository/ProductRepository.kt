package com.ampersand.myshop.modules.products.domain.repository

import arrow.core.Either
import com.ampersand.myshop.modules.products.domain.model.NetworkError
import com.ampersand.myshop.modules.products.domain.model.ProductModel

interface ProductRepository {
    suspend fun getProducts(): Either<NetworkError,List<ProductModel>>
}