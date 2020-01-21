package com.app.final_kumar_shubham.home

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.app.final_kumar_shubham.authentication.createacount.UserCreateAcountModel
import com.app.final_kumar_shubham.data.model.MessageHandler
import com.app.final_kumar_shubham.data.model.UserModel
import com.app.final_kumar_shubham.data.remote.FirebaseHandler
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

/**
 * @author Kumar Shubham
 * 10/1/20
 */
class HomeViewModel:ViewModel() {
    var userName = MutableLiveData<String>()
    var userEmail = MutableLiveData<String>()
    var userDob = MutableLiveData<String>()
    var errorHandler = MutableLiveData<MessageHandler>()

    var dataget = MutableLiveData<ArrayList<UserModel>>()
    var userList = ArrayList<UserModel>()

    init {
        userEmail.value=""
        userName.value=""
        userDob.value=""
    }


    fun onclickAdd(){

        errorHandler.value =MessageHandler(
            "" + " add Working", 1)
    }
    fun onClickSignout(){

        FirebaseAuth.getInstance().signOut()
        errorHandler.value =MessageHandler(
            "" + " signout ", 2)

    }


    fun getdata() {
        ///  Firebase get data
        FirebaseDatabase.getInstance().getReference("users").child("data")
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    // Get Post object and use the values to update the UI
                    userList.clear()
                    for (postSnapshot in dataSnapshot.children) {
                        //getting artist
                        val data = postSnapshot.getValue<UserModel>(UserModel::class.java)
                        //adding artist to the list
                        userList.add(data!!)
                    }
                    dataget.postValue(userList)
                }

                override fun onCancelled(databaseError: DatabaseError) {
                    // Getting Post failed, log a message
                    Log.w("TAG", "loadPost:onCancelled", databaseError.toException())
                    // ...
                }
            })


    }

    //Fearching curent user name

    fun fetchUsers() {
        val userId=FirebaseAuth.getInstance().uid
        val ref = FirebaseDatabase.getInstance().getReference("/users").child("alluser").child("$userId")
        ref.addListenerForSingleValueEvent(object : ValueEventListener {

            override fun onDataChange(p0: DataSnapshot) {
                    val user = p0.getValue(UserCreateAcountModel::class.java)
                    if (user != null) {
                        val mName = user.username
                        var userAge=user.dob
                        userName.value = mName
                        userDob.value=userAge
                }
            }

            override fun onCancelled(p0: DatabaseError) {
            }
        })

    }


}