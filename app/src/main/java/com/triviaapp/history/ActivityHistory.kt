package com.triviaapp.history

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.triviaapp.R
import com.triviaapp.adapter.HistoryAdapter
import com.triviaapp.databinding.ActivityHistoryBinding
import com.triviaapp.databinding.ActivitySummaryBinding
import com.triviaapp.localDB.DBHelper
import com.triviaapp.model.QuizHistory
import com.triviaapp.viewModel.FirstPageViewModel

class ActivityHistory : AppCompatActivity() {
    lateinit var viewModel: FirstPageViewModel
    lateinit var adapter: HistoryAdapter
    private lateinit var linearLayoutManager: LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivityHistoryBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_history)
        viewModel = ViewModelProvider(this).get(FirstPageViewModel::class.java)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        var recycler: RecyclerView = findViewById(R.id.rv_history)

        linearLayoutManager = LinearLayoutManager(this)
        recycler.layoutManager = linearLayoutManager



        var myDB = DBHelper(applicationContext)
        var arrayList: ArrayList<QuizHistory> = myDB.getAllRecentCallList()!!
        Log.d("list data", arrayList.toString() + arrayList[0].name)

        adapter = HistoryAdapter(this, arrayList, object : HistoryAdapter.onItemClickListener{
            override fun itemClicked(name: String?) {

            }
        })

        recycler.adapter = adapter

    }
}