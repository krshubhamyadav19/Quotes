package com.app.final_kumar_shubham.authentication.signin

import android.util.Patterns
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.app.final_kumar_shubham.data.model.MessageHandler
import com.google.firebase.auth.FirebaseAuth

/**
 * @author Kumar Shubham
 * 10/1/20
 */
class AuthSignInViewModel : ViewModel() {


    var email = MutableLiveData<String>()
    var password = MutableLiveData<String>()
    var errorHandler = MutableLiveData<MessageHandler>()


    init {
        email.value = ""
        password.value = ""
    }


    fun onClickSignIn() {

        loginUserToFirebase()

    }

    fun onClickCreateAcount() {
        errorHandler.value = MessageHandler(
            "" + " Creat acount Working", 1
        )
    }


    private fun loginUserToFirebase() {
        if (!Patterns.EMAIL_ADDRESS.matcher(email.value.toString()).matches()) {
            errorHandler.value = MessageHandler(
                "" + " Opss!! something wrong in email", 0
            )


        } else if (password.value.toString().isEmpty()) {
            errorHandler.value = MessageHandler(
                "" + " Opss!! something wrong in password", 0
            )

        } else {

            val email = email.value.toString()
            val pasword = password.value.toString()

            FirebaseAuth.getInstance().signInWithEmailAndPassword(email, pasword)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        errorHandler.value = MessageHandler(
                            "" + " login Sucessfully", 2
                        )


                    } else {
                        errorHandler.value = MessageHandler(
                            "" + " login failed", 0
                        )


                    }
                }
        }


    }

}