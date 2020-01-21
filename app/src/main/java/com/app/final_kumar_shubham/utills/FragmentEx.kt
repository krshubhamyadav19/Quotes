package com.app.assignment_5_kumarshubham.utills

import android.app.Activity
import android.widget.Toast
import androidx.fragment.app.Fragment

/**
 * @author Kumar Shubham
 * 4/1/20
 */



fun Fragment.showToast(message:String)
{
    Toast.makeText(context,"$message", Toast.LENGTH_SHORT).show()
}