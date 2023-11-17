package com.example.bankeurasia.utils

interface Validator {
    fun isValid(): Boolean
}

fun List<Validator>.isValid(): Boolean {
    for (validator in this) {
        if (!validator.isValid()) {
            return false
        }
    }
    return true
}