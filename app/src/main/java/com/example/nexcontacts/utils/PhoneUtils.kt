package com.example.nexcontacts.utils

object PhoneUtils {

    // +90555... veya 0555... formatlarını kabul eden örnek regex
    private val phoneRegex = Regex("^\\+?[0-9]{7,15}\$")

    fun isValidPhone(number: String): Boolean {
        return phoneRegex.matches(number.trim())
    }
}
