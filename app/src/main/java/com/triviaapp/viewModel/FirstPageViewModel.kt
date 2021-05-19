package com.triviaapp.viewModel

import android.app.Application
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.triviaapp.R
import com.triviaapp.model.QuizHistory

class FirstPageViewModel(private val app: Application) : AndroidViewModel(app) {

    var model = QuizHistory()
    var goToQuizPage = MutableLiveData<Boolean>()

    fun nextClicked(view: View){
        if (model.name?.isNullOrEmpty()!!){
            Toast.makeText(app, app.resources.getString(R.string.empty_name), Toast.LENGTH_LONG).show()
            return
        }
        goToQuizPage.value = true
        //Toast.makeText(app, "clicked", Toast.LENGTH_LONG).show()
    }

    fun getName(): TextWatcher {
        return object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}

            override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}

            override fun afterTextChanged(editable: Editable) {
                model.name = editable.toString()
            }
        }
    }
}