package com.example.myapplication

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView


class TaskAdapter(context: Context, tasks: ArrayList<Task>) :
    ArrayAdapter<Task>(context, 0, tasks) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var itemView = convertView
        val task = getItem(position)

        if (itemView == null) {
            itemView = LayoutInflater.from(context).inflate(R.layout.task_item, parent, false)
        }

        val taskNameTextView = itemView?.findViewById<TextView>(R.id.task_name)
        val taskCheckbox = itemView?.findViewById<CheckBox>(R.id.task_checkbox)
        val deleteButton = itemView?.findViewById<Button>(R.id.delete_button)

        taskNameTextView?.text = task?.name
        taskCheckbox?.isChecked = task?.isCompleted ?: false

        taskCheckbox?.setOnCheckedChangeListener { _, isChecked ->
            task?.isCompleted = isChecked
        }

        deleteButton?.setOnClickListener {
            remove(task)
            notifyDataSetChanged()
        }

        return itemView!!
    }
}
