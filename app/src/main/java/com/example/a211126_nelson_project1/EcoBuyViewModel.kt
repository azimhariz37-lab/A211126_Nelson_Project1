package com.example.a211126_nelson_project1

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class EcoBuyViewModel : ViewModel() {

    var userName = mutableStateOf("")
        private set

    var isVerified = mutableStateOf(false)
        private set

    var isLoggedIn = mutableStateOf(false)
        private set

    var favoriteProducts = mutableStateListOf<EcoProduct>()

    var email = mutableStateOf("")
        private set

    var phone = mutableStateOf("")
        private set

    var profileImage = mutableStateOf<Int?>(null) // drawable (for now)
        private set

    var password = mutableStateOf("")
        private set

    fun updateName(name: String) {
        userName.value = name
    }

    fun verifyUser() {
        isVerified.value = true
    }

    fun updateEmail(newEmail: String) {
        email.value = newEmail
    }

    fun updatePhone(newPhone: String) {
        phone.value = newPhone
    }

    fun updatePassword(newPassword: String) {
        password.value = newPassword
    }

    fun updateProfileImage(imageRes: Int) {
        profileImage.value = imageRes
    }

    fun login() {
        if (userName.value.isNotBlank()) {
            isLoggedIn.value = true
        }
    }

    fun logout() {
        isLoggedIn.value = false
        isVerified.value = false

        userName.value = ""
        email.value = ""
        phone.value = ""
        password.value = ""
        profileImage.value = null

        favoriteProducts.clear()
    }

    fun toggleFavorite(product: EcoProduct) {
        if (favoriteProducts.contains(product)) {
            favoriteProducts.remove(product)
        } else {
            favoriteProducts.add(product)
        }
    }
}