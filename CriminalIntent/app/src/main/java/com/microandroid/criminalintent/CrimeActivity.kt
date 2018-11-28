package com.microandroid.criminalintent

import android.support.v7.app.AppCompatActivity
import android.os.Bundle

class CrimeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crime)

        val sfm = supportFragmentManager
        var fragment = sfm.findFragmentById(R.id.fragment_container)
        if (fragment == null) {
            fragment = CrimeFragment()
            sfm.beginTransaction()
                    .add(R.id.fragment_container, fragment)
                    .commit()
        }
    }
}
