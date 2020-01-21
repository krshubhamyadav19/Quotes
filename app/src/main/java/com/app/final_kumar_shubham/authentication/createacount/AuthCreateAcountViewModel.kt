package com.app.final_kumar_shubham.authentication.createacount

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.app.final_kumar_shubham.data.model.MessageHandler
import com.app.final_kumar_shubham.data.remote.FirebaseHandler
import com.google.firebase.auth.FirebaseAuth

/**
 * @author Kumar Shubham
 * 10/1/20
 */
class AuthCreateAcountViewModel(var firebaseHandler: FirebaseHandler) : ViewModel() {

    var email = MutableLiveData<String>()
    var password = MutableLiveData<String>()
    var userName = MutableLiveData<String>()
    var dob = MutableLiveData<String>()
    var errorHandler = MutableLiveData<MessageHandler>()


    init {
        email.value = ""
        password.value = ""
        userName.value = ""
        dob.value = ""
    }


    fun onClickSignIn() {
        errorHandler.value = MessageHandler(
            "" + " signIn Working", 1
        )

    }

    fun onClickSignup() {
        performRegister()
        errorHandler.value = MessageHandler(
            "" + " sinup Working", 0
        )

    }


    private fun performRegister() {
        val mUserEmail = email.value.toString()
        val mPassword = password.value.toString()
        val mUserName = userName.value.toString()

        if (mUserEmail.isEmpty() || mPassword.isEmpty() || mUserName.isEmpty()) {
            errorHandler.value = MessageHandler(
                "" + " Oops!! some field are empty", 0
            )

        }else{
            // signup with firebase

            FirebaseAuth.getInstance().createUserWithEmailAndPassword(mUserEmail, mPassword)
                .addOnCompleteListener {
                    if (!it.isSuccessful) return@addOnCompleteListener
                    saveUserToFirebase()

                }
                .addOnFailureListener {
                }
        }

    }

    //saving signup detail to firebase
    private fun saveUserToFirebase() {
        val uid = FirebaseAuth.getInstance().uid
        val username: String = userName.value.toString()
        val dob: String = dob.value.toString()
        val user = UserCreateAcountModel(uid!!, username, dob)

        firebaseHandler.getUserReference().child("alluser").child("$uid").setValue(user)
            .addOnSuccessListener {
                errorHandler.value = MessageHandler(
                    "" + " Welcome ${FirebaseAuth.getInstance().currentUser}", 3
                )
            }

    }


}