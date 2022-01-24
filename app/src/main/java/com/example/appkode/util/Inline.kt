package com.example.appkode.util

inline  fun <S,R> NetworkResponse<S>.mapResponse(block : (S) -> R): NetworkResponse<R>{

    return when(this){
        is NetworkResponse.Success -> NetworkResponse.Success(data = block(this.data))
        is NetworkResponse.Error -> NetworkResponse.Error("error")
    }
}
inline fun <S> NetworkResponse<S>.doOnSuccess(block: (S) -> Unit): NetworkResponse<S> {
    if (this is NetworkResponse.Success) {
        block(this.data)
    }
    return this
}