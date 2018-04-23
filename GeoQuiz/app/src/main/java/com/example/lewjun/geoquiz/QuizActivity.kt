package com.example.lewjun.geoquiz

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Gravity
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class QuizActivity : AppCompatActivity() {

    private lateinit var mTrueButton: Button
    private lateinit var mFalseButton: Button
    private lateinit var mQuestionTextView: TextView
    private lateinit var mNextButton: Button

    // 问题仓库
    private var mQuestionBank = arrayOf(
            Question(R.string.question_australia, true)
            , Question(R.string.question_oceans, true)
            , Question(R.string.question_mideast, false)
            , Question(R.string.question_africa, false)
            , Question(R.string.question_americas, true)
            , Question(R.string.question_asia, true)
    )

    // 当前问题下标
    private var mCurrentQuestionIndex = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)

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

        mQuestionTextView = findViewById(R.id.question_text_view)
        mQuestionTextView.setOnClickListener {
            updateQuestionToNext()
        }
        updateQuestion()
    }

    /**
     * 显示下一个问题
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
        val msgResId = if (isUserPressedTrue == isAnswerTrue) {
            R.string.correct_toast
        } else {
            R.string.incorrect_toast
        }
        showMsg(msgResId)
    }

    /**
     * 显示toast消息
     */
    private fun showMsg(msgResId: Int) {
        val toast = Toast.makeText(this@QuizActivity, msgResId, Toast.LENGTH_SHORT)
        toast.setGravity(Gravity.TOP, 0, 0)
        toast.show()
    }

    /**
     * 更新问题
     */
    private fun updateQuestion() {
        mQuestionTextView.setText(mQuestionBank[mCurrentQuestionIndex].textResId)
    }
}
