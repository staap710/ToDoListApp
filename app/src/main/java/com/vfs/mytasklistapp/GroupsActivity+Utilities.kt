package com.vfs.mytasklistapp

import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager

fun GroupsActivity.cacheOutlets ()
{
    groupsRecyclerView = findViewById(R.id.groupsRecyclerView_id)
    groupsRecyclerView.layoutManager = LinearLayoutManager (this)
    groupsRecyclerView.adapter = GroupAdapter(this)

    newGroupButton = findViewById(R.id.newGroupButton_id)
    newGroupButton.setOnClickListener(createNewGroup())
}

fun GroupsActivity.createNewGroup () : View.OnClickListener
{
    return View.OnClickListener()
    {
        val builder = AlertDialog.Builder(this)
        builder.setMessage("Enter a name for your new group")
        builder.setTitle("New Group")

        // guard rails for entry validation

        val input = EditText(this)
        builder.setView(input)

        builder.setNegativeButton("Cancel") { _, _ -> }
        builder.setPositiveButton("Add") { _, _ ->

            val name = input.text.toString()
            val newGroup = Group(name)
            AppData.groups.add(newGroup)
            groupsRecyclerView.adapter?.notifyItemInserted(AppData.groups.size - 1)
        }

        builder.create().show()
    }
}











