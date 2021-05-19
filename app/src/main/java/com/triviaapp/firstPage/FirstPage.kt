package com.triviaapp.firstPage

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.triviaapp.R
import com.triviaapp.databinding.ActivityFirstPageBinding
import com.triviaapp.quizPage.QuizPage
import com.triviaapp.viewModel.FirstPageViewModel
import java.text.SimpleDateFormat
import java.util.*

class FirstPage : AppCompatActivity() {
    private lateinit var viewModel: FirstPageViewModel
    @SuppressLint("SimpleDateFormat")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivityFirstPageBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_first_page)
        viewModel = ViewModelProvider(this).get(FirstPageViewModel::class.java)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        viewModel.goToQuizPage.observe(this, Observer {
            if (it){
                finish()
                startActivity(Intent(applicationContext, QuizPage::class.java)
                    .putExtra("name", viewModel.model.name))
            }
        })

    }
}