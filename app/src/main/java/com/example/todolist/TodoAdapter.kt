package com.example.todolist

import android.graphics.Paint.STRIKE_THRU_TEXT_FLAG
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_todo.view.*

class TodoAdapter(private val todosList: MutableList<Todo>):
    RecyclerView.Adapter<TodoAdapter.TodoViewHolder>()
{
    class TodoViewHolder(itemView:View): RecyclerView.ViewHolder(itemView)

    private fun toggleStrikeThrough(tvTodoTitle: TextView, isChecked: Boolean) {
        if(isChecked) {
            tvTodoTitle.paintFlags = tvTodoTitle.paintFlags or STRIKE_THRU_TEXT_FLAG
        } else {
            tvTodoTitle.paintFlags = tvTodoTitle.paintFlags and STRIKE_THRU_TEXT_FLAG.inv()
        }
    }

    fun addTodo(todo: Todo) {
        todosList.add(todo)
        notifyItemInserted(todosList.size - 1)
    }

    fun deleteDoneTodos() {
        todosList.removeAll { todo ->
            todo.isChecked
        }
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int
    {
        return todosList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder
    {
        return TodoViewHolder(LayoutInflater.from(parent.context).inflate(
            R.layout.item_todo,
            parent,
            false
        ))
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int)
    {
        val currentTodo = todosList[position]
        holder.itemView.apply {
            tvTodoTitle.text = currentTodo.title
            cbCheck.isChecked = currentTodo.isChecked
            toggleStrikeThrough(tvTodoTitle, currentTodo.isChecked)
            cbCheck.setOnCheckedChangeListener { _, isChecked ->
                toggleStrikeThrough(tvTodoTitle, isChecked)
                currentTodo.isChecked = !currentTodo.isChecked
        }
    }
}
    }