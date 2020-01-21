package com.app.final_kumar_shubham.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.app.final_kumar_shubham.authentication.createacount.AuthCreateAcountViewModel
import com.app.final_kumar_shubham.data.remote.FirebaseHandler

/**
 * @author Kumar Shubham
 * 10/1/20
 */
class ViewModelFactory(private val firebaseHandler: FirebaseHandler) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(AuthCreateAcountViewModel::class.java)) {
            return AuthCreateAcountViewModel(firebaseHandler) as T
        } else {
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}