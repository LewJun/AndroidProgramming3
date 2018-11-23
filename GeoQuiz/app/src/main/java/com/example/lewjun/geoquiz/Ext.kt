package com.example.lewjun.geoquiz

import android.app.Activity
import android.util.Log
import android.widget.Toast

fun Activity.toast(text: CharSequence, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, text, duration).show()
}

fun Activity.toast(resId: Int, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, resId, duration).show()
}

fun Activity.logi(TAG: String, msg: String) {
    Log.i(TAG, msg)
}