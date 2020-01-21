package com.app.final_kumar_shubham.base

import com.app.final_kumar_shubham.data.remote.FirebaseHandler

/**
 * @author Kumar Shubham
 * 10/1/20
 */
class FirebaseHandlerInit {

    companion object {
        val firebaseHandler: FirebaseHandler get() = FirebaseHandler()

    }
}