package com.ampersand.myshop.modules.products.data.repository

import arrow.core.Either
import com.ampersand.myshop.modules.products.data.mapper.toNetworkError
import com.ampersand.myshop.modules.products.data.remote.ProductsApi
import com.ampersand.myshop.modules.products.domain.model.NetworkError
import com.ampersand.myshop.modules.products.domain.model.ProductModel
import com.ampersand.myshop.modules.products.domain.repository.ProductRepository
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(
    private val productsApi: ProductsApi
): ProductRepository {

    override suspend fun getProducts(): Either<NetworkError, List<ProductModel>> {
        return Either.catch {
            productsApi.getProducts()
        }.mapLeft { it.toNetworkError() }
    }
}