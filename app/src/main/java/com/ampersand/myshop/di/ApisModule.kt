package com.ampersand.myshop.di

import com.ampersand.myshop.modules.products.data.remote.ProductsApi
import com.ampersand.myshop.util.RetroInstance.Companion.getRetroInstance
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object ApisModule {

    @Provides
    @Singleton
    fun provideProductsApi(): ProductsApi {
        return getRetroInstance()
            .create(ProductsApi::class.java)
    }
}