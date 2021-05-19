package com.triviaapp.quizPage

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.triviaapp.R
import com.triviaapp.databinding.ActivityQuizPageBinding
import com.triviaapp.secondQuestion.SecondQuestion
import com.triviaapp.viewModel.FirstPageViewModel

class QuizPage : AppCompatActivity() {
    private lateinit var viewModel: FirstPageViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivityQuizPageBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_quiz_page)
        viewModel = ViewModelProvider(this).get(FirstPageViewModel::class.java)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        var btnNext: Button = findViewById(R.id.btn_next)
        var rg: RadioGroup = findViewById(R.id.rg_g1)
        var q1: TextView = findViewById(R.id.tv_q1)

        var intent = intent
        var name= intent.getStringExtra("name")
        btnNext.setOnClickListener {
            val selected: Int = rg.checkedRadioButtonId
            if (selected != -1) {
                var selectedRadioButton : RadioButton = findViewById(selected)
                val string: String = selectedRadioButton.text.toString()
                viewModel.model.question1 = q1.text.toString()
                viewModel.model.answer1 = string
                finish()
                startActivity(Intent(applicationContext, SecondQuestion::class.java)
                    .putExtra("name", name)
                    .putExtra("q1", viewModel.model.question1)
                    .putExtra("answer1", viewModel.model.answer1))
                //Toast.makeText(applicationContext, string, Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(applicationContext, resources.getString(R.string.nothig_selected), Toast.LENGTH_SHORT).show()
            }
        }
    }
}