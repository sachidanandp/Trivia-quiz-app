package com.triviaapp.summary

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.triviaapp.R
import com.triviaapp.databinding.ActivitySecondQuestionBinding
import com.triviaapp.databinding.ActivitySummaryBinding
import com.triviaapp.firstPage.FirstPage
import com.triviaapp.history.ActivityHistory
import com.triviaapp.localDB.DBHelper
import com.triviaapp.model.QuizHistory
import com.triviaapp.viewModel.FirstPageViewModel

class Summary : AppCompatActivity() {

    lateinit var viewModel: FirstPageViewModel
    lateinit var myDB: DBHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivitySummaryBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_summary)
        viewModel = ViewModelProvider(this).get(FirstPageViewModel::class.java)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        myDB = DBHelper(applicationContext)
        var arrayList: ArrayList<QuizHistory> = myDB.getAllRecentCallList()!!
        viewModel.model.name = "Hello: "+ arrayList[0].name
        viewModel.model.question1 = "Question1: "+ arrayList[0].question1
        viewModel.model.answer1 = "Answer1: "+ arrayList[0].answer1
        viewModel.model.answer2 = "Answer2: "+ arrayList[0].answer2
        viewModel.model.question2 = "Question2: "+ arrayList[0].question2

        var btnFinish: Button = findViewById(R.id.btn_finish)
        var btnHistory: Button = findViewById(R.id.btn_history)

        btnFinish.setOnClickListener {
            finish()
            startActivity(Intent(applicationContext, FirstPage::class.java))
        }
        btnHistory.setOnClickListener {
            finish()
            startActivity(Intent(applicationContext, ActivityHistory::class.java))
        }

    }
}