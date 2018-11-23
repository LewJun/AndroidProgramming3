package com.example.lewjun.geoquiz

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import java.text.DecimalFormat

class QuizActivity : AppCompatActivity() {

    private val TAG = "QuizActivity"
    private lateinit var mTrueButton: Button
    private lateinit var mFalseButton: Button
    private lateinit var mQuestionTextView: TextView
    private lateinit var mNextButton: ImageButton
    private lateinit var mPrevButton: ImageButton

    // 问题仓库
    private var mQuestionBank = arrayOf(
            Question(R.string.question_australia, true)
            , Question(R.string.question_oceans, true)
            , Question(R.string.question_mideast, false)
            , Question(R.string.question_africa, false)
            , Question(R.string.question_americas, true)
            , Question(R.string.question_asia, true)
    )

    /** 当前问题下标 */
    private var mCurrentQuestionIndex = 0

    private val QUESTION_INDEX = "QUESTION_INDEX"

    /** 问题回答详情 */
    private val mAnsweredInfo = mutableMapOf<Int, Boolean>()

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        outState?.putInt(QUESTION_INDEX, mCurrentQuestionIndex)
    }

    // 1 创建Activity实例时调用（在内存中）
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        logi(TAG, "onCreate called")
        setContentView(R.layout.activity_quiz)

        mCurrentQuestionIndex = savedInstanceState?.getInt(QUESTION_INDEX, 0) ?: 0

        mTrueButton = findViewById(R.id.true_button)
        mTrueButton.setOnClickListener {
            checkAnswer(true)
        }

        mFalseButton = findViewById(R.id.false_button)
        mFalseButton.setOnClickListener {
            checkAnswer(false)
        }

        mNextButton = findViewById(R.id.next_button)
        mNextButton.setOnClickListener {
            updateQuestionToNext()
        }

        mPrevButton = findViewById(R.id.prev_button)
        mPrevButton.setOnClickListener {
            updateQuestionToPrev()
        }

        mQuestionTextView = findViewById(R.id.question_text_view)
        mQuestionTextView.setOnClickListener {
            updateQuestionToNext()
        }
        updateQuestion()
    }

    // 2 屏幕可见或重新可见后调用
    override fun onStart() {
        super.onStart()
        logi(TAG, "onStart called")
    }

    // 3 屏幕可见或重新可见后调用，在onStart后调用
    override fun onResume() {
        super.onResume()
        logi(TAG, "onResume called")
    }

    // 4 点主屏幕键和最近应用键会调用
    override fun onPause() {
        super.onPause()
        logi(TAG, "onPause called")
    }

    // 5 点主屏幕键和最近应用键会调用，在onPause之后
    override fun onStop() {
        super.onStop()
        logi(TAG, "onStop called")
    }

    // 6 后退会销毁activity实例
    override fun onDestroy() {
        super.onDestroy()
        logi(TAG, "onDestroy called")
    }

    // 旋转设备会依次调用
    // onPause
    // onStop
    // onDestroy
    // onCreate
    // onStart
    // onResume
    // 即Activity会销毁，然后重新建立

    /**
     * 显示上一个题目
     */
    private fun updateQuestionToPrev() {
        val size = mQuestionBank.size
        mCurrentQuestionIndex = ((mCurrentQuestionIndex - 1) + size) % size
        updateQuestion()
    }

    /**
     * 显示下一个题目
     */
    private fun updateQuestionToNext() {
        mCurrentQuestionIndex = (mCurrentQuestionIndex + 1) % mQuestionBank.size
        updateQuestion()
    }

    /**
     * 根据用户点击的按钮和实际问题答案相比较
     */
    private fun checkAnswer(isUserPressedTrue: Boolean) {
        val isAnswerTrue = mQuestionBank[mCurrentQuestionIndex].answerTrue
        toast(
                if (isUserPressedTrue == isAnswerTrue) {
                    R.string.correct_toast
                } else {
                    R.string.incorrect_toast
                }
        )

        mAnsweredInfo[mCurrentQuestionIndex] = isUserPressedTrue

        mTrueButton.isEnabled = false
        mFalseButton.isEnabled = false

        if (mAnsweredInfo.size == mQuestionBank.size) {
            val successCount = mAnsweredInfo.filter { it.value == mQuestionBank[it.key].answerTrue }.count()
            toast("The success rate ${DecimalFormat("##.##%")
                    .format(successCount / mAnsweredInfo.size)}")
        }
    }

    /**
     * 更新问题
     */
    private fun updateQuestion() {
        logi("Tag", mCurrentQuestionIndex.toString())
        mQuestionTextView.setText(mQuestionBank[mCurrentQuestionIndex].textResId)
        val b = mAnsweredInfo[mCurrentQuestionIndex]
        if (b != null) {
            mTrueButton.isEnabled = false
            mFalseButton.isEnabled = false
            toast("You've answered $b")
        } else {
            mTrueButton.isEnabled = true
            mFalseButton.isEnabled = true
        }
    }
}
