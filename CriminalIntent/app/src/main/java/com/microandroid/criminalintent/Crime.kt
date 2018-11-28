package com.microandroid.criminalintent

import java.util.*

data class Crime(
        val mId: UUID = UUID.randomUUID(),
        val mTitle: String,
        val mDate: Date = Date(),
        val mSolved: Boolean
)