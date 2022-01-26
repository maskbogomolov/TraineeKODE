package com.example.appkode.data.database

import android.content.SharedPreferences
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SharedPreferences @Inject constructor(
    val sharedPreferences: SharedPreferences
) {
    val mutex = Mutex()

    suspend fun isFilter(key: String): Boolean {
        mutex.withLock {
            return sharedPreferences.getBoolean(key, false)
        }
    }

    suspend fun saveFilterMode(key: String, value: Boolean) {
        if (value) {
            mutex.withLock {
                withContext(Dispatchers.IO) {
                    sharedPreferences.edit().putBoolean(key, true).commit()
                }
            }
        } else {
            mutex.withLock {
                withContext(Dispatchers.IO) {
                    sharedPreferences.edit().remove(key).commit()
                }
            }

        }
    }

}