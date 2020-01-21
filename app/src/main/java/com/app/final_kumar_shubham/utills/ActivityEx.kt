package com.app.assignment_5_kumarshubham.utills

import android.app.Activity
import android.content.Context
import android.widget.Toast

/**
 * @author Kumar Shubham
 * 4/1/20
 */


fun Activity.showToast(message:String)
{
    Toast.makeText(this,"$message",Toast.LENGTH_SHORT).show()
}