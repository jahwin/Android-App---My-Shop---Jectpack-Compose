package com.ampersand.myshop.di

import com.ampersand.myshop.modules.products.data.repository.ProductRepositoryImpl
import com.ampersand.myshop.modules.products.domain.repository.ProductRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract  class RepositoryModule {

    @Binds
    @Singleton
    abstract  fun bindProductRepository(productRepositoryImpl: ProductRepositoryImpl): ProductRepository
}