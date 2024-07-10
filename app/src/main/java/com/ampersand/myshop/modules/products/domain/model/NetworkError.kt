package com.ampersand.myshop.modules.products.domain.model

data class NetworkError(
    var error:ApiError,
    val t: Throwable? = null
)

enum class ApiError(var message: String) {
   NetworkError("Network Error"),
    UnknownResponse("Unknown Error"),
    UnknownError("Unknown Error")
}