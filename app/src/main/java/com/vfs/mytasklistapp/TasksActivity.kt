package com.vfs.mytasklistapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.widget.EditText
import androidx.appcompat.app.AlertDialog

class TasksActivity : AppCompatActivity()
{
    lateinit var selectedGroupNameTextView: TextView
    lateinit var goToGroupsButton: Button

    lateinit var tasksRecyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.tasks_layout)

        selectedGroupNameTextView = findViewById(R.id.selectedGroupNameTextView_id)
        goToGroupsButton = findViewById(R.id.goToGroupsButton_id)
        val newTaskButton = findViewById<Button>(R.id.newTaskButton_id)
        tasksRecyclerView = findViewById(R.id.tasksRecyclerView_id)

        // Get group based on position
        val position = intent.getIntExtra("position", 0)
        val group = AppData.groups[position]
        selectedGroupNameTextView.text = group.name

        // RecyclerView setup
        tasksRecyclerView.layoutManager = LinearLayoutManager(this)
        tasksRecyclerView.adapter = TaskAdapter(group)

        // Go back to group list
        goToGroupsButton.setOnClickListener {
            finish()
        }

        // Add new task
        newTaskButton.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            builder.setTitle("New Task")
            builder.setMessage("Enter task name")

            val input = EditText(this)
            builder.setView(input)

            builder.setNegativeButton("Cancel") { _, _ -> }

            builder.setPositiveButton("Add") { _, _ ->
                val taskName = input.text.toString()
                if (taskName.isNotBlank()) {
                    group.tasks.add(Task(taskName))
                    tasksRecyclerView.adapter?.notifyItemInserted(group.tasks.size - 1)
                }
            }

            builder.show()
        }
    }
}

class TaskViewHolder (rootLayout: LinearLayout) : RecyclerView.ViewHolder (rootLayout)
{
    val taskNameTextView = rootLayout.findViewById<TextView>(R.id.taskNameTextView_id)
    val taskCheckBox = rootLayout.findViewById<CheckBox>(R.id.taskCheckBox_id)

    fun bind (task: Task)
    {
        taskNameTextView.text = task.name
        taskCheckBox.isChecked = task.completed
    }
}

class TaskAdapter (val group: Group) : RecyclerView.Adapter<TaskViewHolder>()
{
    override fun getItemCount(): Int  = group.tasks.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder
    {
        val rootLinearLayout = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.task_row,
                parent,
                false) as LinearLayout

        return TaskViewHolder (rootLinearLayout)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int)
    {
        val task = group.tasks[position]
        holder.bind(task)

        // Toggle checkbox
        holder.itemView.setOnClickListener {
            task.completed = !task.completed
            notifyItemChanged(position)
        }

        // Long click to delete task
        holder.itemView.setOnLongClickListener {
            group.tasks.removeAt(position)
            notifyItemRemoved(position)
            true
        }
    }

}











