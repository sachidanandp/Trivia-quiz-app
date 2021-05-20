package com.triviaapp.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.triviaapp.R
import com.triviaapp.adapter.HistoryAdapter.*
import com.triviaapp.model.QuizHistory
import java.util.*

class HistoryAdapter(
    var context1: Context,
    var data: ArrayList<QuizHistory>,
    var itemClickListener: onItemClickListener
) :
    RecyclerView.Adapter<HistoryAdapter.ViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val listItem = layoutInflater.inflate(R.layout.history_listview, parent, false)
        return ViewHolder(
            listItem
        )
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {
      /*  val model = data[position]
        holder.date.text = model.dateTime
        holder.name.text = model.name
        holder.q1.text = model.question1
        holder.q2.text = model.question2
        holder.ans1.text = model.answer1
        holder.ans2.text = model.answer2*/

        //Log.d("name", model.name!!)

    }

    override fun getItemCount(): Int {
        return 5
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var date: TextView = itemView.findViewById(R.id.tv_date)
        var name: TextView = itemView.findViewById(R.id.tv_name)
        var q1: TextView = itemView.findViewById(R.id.tv_q1)
        var ans1: TextView = itemView.findViewById(R.id.tv_ans1)
        var ans2: TextView = itemView.findViewById(R.id.tv_ans2)
        var q2: TextView = itemView.findViewById(R.id.tv_q2)
        var layout: ConstraintLayout = itemView.findViewById(R.id.layout)

    }

    interface onItemClickListener {
        fun itemClicked(name: String?)

    }

}