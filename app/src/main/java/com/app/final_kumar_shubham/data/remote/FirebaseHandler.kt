package com.app.final_kumar_shubham.data.remote

import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

/**
 * @author Kumar Shubham
 * 10/1/20
 */
class FirebaseHandler {


    companion object {
        val DB = FirebaseDatabase.getInstance()
    }

    //get database refrence
    fun getUserReference(): DatabaseReference {
        return DB.getReference("users")
    }




   
}