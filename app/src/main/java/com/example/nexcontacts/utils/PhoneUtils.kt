package com.example.nexcontacts.utils

object PhoneUtils {

    private val phoneRegex = Regex("^\\+?[0-9]{7,15}\$")

    fun isValidPhone(number: String): Boolean {
        return phoneRegex.matches(number.trim())
    }
}
