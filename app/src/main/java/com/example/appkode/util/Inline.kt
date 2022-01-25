package com.example.appkode.util

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


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
inline fun <T, R> Flow<Iterable<T>>.mapToUserDomain(crossinline transform: suspend (T) -> R): Flow<List<R>> =
    map { list ->
        list.map { item ->
            transform(item)
        }
    }
