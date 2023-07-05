package com.example.myapplication

import android.os.Bundle
import android.widget.AdapterView
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var taskInput: EditText
    private lateinit var addButton: Button
    private lateinit var taskList: ListView
    private lateinit var taskAdapter: TaskAdapter
    private lateinit var tasks: ArrayList<Task>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        taskInput = findViewById(R.id.task_input)
        addButton = findViewById(R.id.add_button)
        taskList = findViewById(R.id.task_list)
        tasks = ArrayList()

        taskAdapter = TaskAdapter(this, tasks)
        taskList.adapter = taskAdapter

        addButton.setOnClickListener {
            val taskText = taskInput.text.toString()
            if (taskText.isNotEmpty()) {
                val task = Task(taskText, false)
                tasks.add(task)
                taskAdapter.notifyDataSetChanged()
                taskInput.text.clear()
            }
        }

        taskList.onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ ->
            val selectedTask = tasks[position]
            tasks.remove(selectedTask)
            taskAdapter.notifyDataSetChanged()
        }
    }
}


data class Task(val name: String, var isCompleted: Boolean)
