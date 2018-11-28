package com.microandroid.criminalintent

import android.os.Bundle
import android.support.v4.app.Fragment
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText

class CrimeFragment : Fragment() {
    private lateinit var mCrime: Crime

    private lateinit var mTitleField: EditText
    private lateinit var mDateButton: Button
    private lateinit var mSolvedCheckBox: CheckBox

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mCrime = Crime()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_crime, container, false)

        initViews(view)

        return view
    }

    /**
     * init views
     */
    fun initViews(view: View) {
        mTitleField = view.findViewById(R.id.crime_title)
        mDateButton = view.findViewById(R.id.crime_date)
        mSolvedCheckBox = view.findViewById(R.id.crime_solved)

        mTitleField.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                mCrime.mTitle = s.toString()
            }
        })

        mDateButton.text = mCrime.mDate.toString()
        mDateButton.isEnabled = false

        mSolvedCheckBox.setOnCheckedChangeListener { buttonView, isChecked ->
            mCrime.mSolved = isChecked
        }
    }


}