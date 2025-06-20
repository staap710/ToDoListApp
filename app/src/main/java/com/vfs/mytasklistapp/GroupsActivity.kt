package com.vfs.mytasklistapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.widget.EditText
import androidx.appcompat.app.AlertDialog


class GroupsActivity : AppCompatActivity(), OnGroupClickedListener
{
    override fun onGroupClicked(position: Int)
    {
        val intent = Intent(this, TasksActivity::class.java)

        intent.putExtra("position", position)

        startActivity(intent)
    }

    override fun onGroupLongClicked(position: Int) {
        val context = this
        val group = AppData.groups[position]

        val options = arrayOf("Edit", "Delete")

        AlertDialog.Builder(context)
            .setTitle("Group Options")
            .setItems(options) { _, which ->
                when (which) {
                    0 -> { // Edit group name
                        val input = EditText(context)
                        input.setText(group.name)

                        AlertDialog.Builder(context)
                            .setTitle("Edit Group Name")
                            .setView(input)
                            .setPositiveButton("Save") { _, _ ->
                                val newName = input.text.toString()
                                if (newName.isNotBlank()) {
                                    AppData.groups[position] = Group(newName, group.tasks)
                                    groupsRecyclerView.adapter?.notifyItemChanged(position)
                                }
                            }
                            .setNegativeButton("Cancel", null)
                            .show()
                    }

                    1 -> { // Delete group
                        AppData.groups.removeAt(position)
                        groupsRecyclerView.adapter?.notifyItemRemoved(position)
                    }
                }
            }
            .show()
    }


    lateinit var newGroupButton: Button
    lateinit var groupsRecyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.groups_layout)

        AppData.initializeGroups()

        cacheOutlets()

        Log.d("GroupViewHolder", "This is On Create")
    }


}










