package com.example.appkode.util

inline  fun <S,R> NetworkResponse<S>.mapResponse(block : (S) -> R): NetworkResponse<R>{

    return when(this){
        is NetworkResponse.Success -> NetworkResponse.Success(data = block(this.data))
        is NetworkResponse.Error -> NetworkResponse.Error("error")
    }
}