package com.example.bankeurasia.base

import javax.inject.Singleton

@Singleton
abstract class BaseRepository {
    inline fun <T> execute(func: () -> T): Result<T> {
        return try {
            Result.success(func())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}