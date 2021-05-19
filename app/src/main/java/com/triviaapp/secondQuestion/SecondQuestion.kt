package com.triviaapp.secondQuestion

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.triviaapp.R
import com.triviaapp.databinding.ActivitySecondQuestionBinding
import com.triviaapp.localDB.DBHelper
import com.triviaapp.summary.Summary
import com.triviaapp.viewModel.FirstPageViewModel
import java.text.SimpleDateFormat
import java.util.*

class SecondQuestion : AppCompatActivity() {

    lateinit var viewModel: FirstPageViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivitySecondQuestionBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_second_question)
        viewModel = ViewModelProvider(this).get(FirstPageViewModel::class.java)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        var btnNext: Button = findViewById(R.id.btn_next)
        var q2: TextView = findViewById(R.id.tv_q1)
        var cb1: CheckBox = findViewById(R.id.cb_op1)
        var cb2: CheckBox = findViewById(R.id.cb_op2)
        var cb3: CheckBox = findViewById(R.id.cb_op3)
        var cb4: CheckBox = findViewById(R.id.cb_op4)

        var intent = intent
        viewModel.model.name= intent.getStringExtra("name")
        viewModel.model.question1= intent.getStringExtra("q1")
        viewModel.model.answer1= intent.getStringExtra("answer1")

        val sdf = SimpleDateFormat("dd'th' MMMM hh:mm a")
        val currentDate = sdf.format(Date())
        println(" C DATE is  $currentDate")
        viewModel.model.dateTime = currentDate

        btnNext.setOnClickListener {
            viewModel.model.question2 = q2.text.toString()
            var ans = ""
            var selected = 0
            if (cb1.isChecked){
                ans += cb1.text.toString() + ", "
                selected+=1
            }
            if (cb2.isChecked){
                ans += cb2.text.toString() + ", "
                selected+=1
            }
            if (cb3.isChecked){
                ans += cb3.text.toString() + ", "
                selected+=1
            }
            if (cb4.isChecked){
                ans += cb4.text.toString()
                selected+=1
            }
            if (selected >0) {
                viewModel.model.answer2 = ans
                var myDB = DBHelper(applicationContext)
                myDB.insertQuiz(viewModel.model)
                finish()
                startActivity(Intent(applicationContext, Summary::class.java))
                //Toast.makeText(applicationContext, string, Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(
                    applicationContext,
                    resources.getString(R.string.nothig_selected),
                    Toast.LENGTH_SHORT
                ).show()
            }

        }
    }
}