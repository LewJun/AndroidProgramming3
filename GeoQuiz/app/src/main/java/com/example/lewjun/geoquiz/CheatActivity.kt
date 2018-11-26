package com.example.lewjun.geoquiz

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.TextView

class CheatActivity : AppCompatActivity() {

    private var mAnswerIsTrue: Boolean = false

    private lateinit var mAnswerTextView: TextView
    private lateinit var mShowAnswerButton: Button

    private var WAS_ANSWER_SHOWN = false

    private val ANSWER_SHOWN = "ANSWER_SHOWN"

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        outState?.let {
            outState.putBoolean(ANSWER_SHOWN, WAS_ANSWER_SHOWN)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cheat)

        mAnswerIsTrue = intent.getBooleanExtra(EXTRA_ANSWER_IS_TRUE, false)

        initViews()

        savedInstanceState?.let {
            if (it.getBoolean(ANSWER_SHOWN, false)) {
                showAnswer()
            }
        }
    }

    private fun initViews() {
        mAnswerTextView = findViewById(R.id.tv_answer)
        mShowAnswerButton = findViewById(R.id.btn_show_answer)
        mShowAnswerButton.setOnClickListener {
            showAnswer()
            it.rotation = it.rotation + 180
        }
    }

    private fun showAnswer() {
        mAnswerTextView.setText(
                if (mAnswerIsTrue) R.string.true_button
                else R.string.false_button
        )

        WAS_ANSWER_SHOWN = true
        setAnswerShowResult(true)
    }

    private fun setAnswerShowResult(isAnswerShown: Boolean) {
        val data = Intent()
        data.putExtra(EXTRA_ANSWER_SHOWN, isAnswerShown)
        setResult(Activity.RESULT_OK, data)
    }

    companion object {
        private const val EXTRA_ANSWER_IS_TRUE = "com.example.lewjun.geoquiz.answer_is_true"
        private const val EXTRA_ANSWER_SHOWN = "com.example.lewjun.geoquiz.answer_shown"

        fun wasAnswerShown(data: Intent) = data.getBooleanExtra(EXTRA_ANSWER_SHOWN, false)

        fun newIntent(context: Context, answerIsTrue: Boolean): Intent {
            val intent = Intent(context, CheatActivity::class.java)
            intent.putExtra(EXTRA_ANSWER_IS_TRUE, answerIsTrue)
            return intent
        }
    }
}
