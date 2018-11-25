package com.example.lewjun.geoquiz

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
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

val Activity.thisActivity get() = this

fun <T : Activity> Activity.launch(context: Context, cls: Class<T>) {
    context.startActivity(Intent(context, cls))
}

fun <T : Activity> Activity.launch(cls: Class<T>) {
    this.startActivity(Intent(this, cls))
}

fun <T : Activity> Activity.launch(cls: Class<T>, extras: Bundle) {
    val intent = Intent(this, cls)
    intent.putExtras(extras)
    this.startActivity(intent)
}