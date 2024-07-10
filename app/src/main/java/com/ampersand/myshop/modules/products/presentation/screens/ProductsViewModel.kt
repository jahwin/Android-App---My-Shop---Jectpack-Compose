package com.ampersand.myshop.modules.products.presentation.screens

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ampersand.myshop.modules.products.domain.repository.ProductRepository
import com.ampersand.myshop.util.Event
import com.ampersand.myshop.util.EventBus.sendEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductsViewModel @Inject constructor(
    private val productRepository: ProductRepository
) : ViewModel() {

    private  val _productsViewState = MutableStateFlow(ProductsViewState())
    val productsViewState = _productsViewState.asStateFlow()
    var productViewState = MutableLiveData<ProductViewState>()

    init {
        getProducts();
    }
    private fun getProducts() {
        viewModelScope.launch {
            _productsViewState.update {
                it.copy(isLoading = true)
            }
            productRepository.getProducts()
                .onRight { products ->
                    _productsViewState.update {
                        it.copy(isLoading = false, products = products)
                    }
                }.onLeft { error ->
                    _productsViewState.update {
                        it.copy(isLoading = false, error = error.error.message)
                    }
                    sendEvent(Event.ShowToast(error.error.message))
                }
        }
    }
}