package com.example.tasks1

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RvListAdapter: RecyclerView.Adapter<RvListAdapter.RvItemHolder>() {
    val todoList = mutableListOf<Todo>()

    class RvItemHolder(val view: View): RecyclerView.ViewHolder(view) {
        fun bindData(todo: Todo) {
            val tvTitle = view.findViewById<TextView>(R.id.tvTitle)
            val tvDesc = view.findViewById<TextView>(R.id.tvDesc)
            tvTitle.text = todo.todoTitle
            tvDesc.text = todo.todoDesc
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RvItemHolder {
        var layoutView = LayoutInflater.from(parent.context).inflate(R.layout.rv_item_adapter, null)
        return RvItemHolder(layoutView)
    }

    override fun getItemCount(): Int = todoList.size

    override fun onBindViewHolder(holder: RvItemHolder, position: Int) {
        holder.bindData(todoList[position])
    }
}