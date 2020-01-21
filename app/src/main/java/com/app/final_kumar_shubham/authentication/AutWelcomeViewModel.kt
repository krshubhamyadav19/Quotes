package com.app.final_kumar_shubham.authentication

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.app.final_kumar_shubham.data.model.MessageHandler

/**
 * @author Kumar Shubham
 * 9/1/20
 */
class AutWelcomeViewModel : ViewModel() {


    var errorHandler = MutableLiveData<MessageHandler>()

    fun onClickSignin() {

        errorHandler.value = MessageHandler(
            "" + " Signup Working", 0
        )


    }

    fun onClickCreateAcount() {
        errorHandler.value = MessageHandler(
            "" + " create acount Working", 1
        )


    }
}