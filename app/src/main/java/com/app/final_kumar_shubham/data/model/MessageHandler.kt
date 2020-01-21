package com.app.final_kumar_shubham.data.model

/**
 * @author Kumar Shubham
 * 10/1/20
 */

class MessageHandler(ErrorMessage: String, ErrorField: Int) {
    val message: String
    val type: Int


    init {
        message = ErrorMessage
        type = ErrorField
    }
}