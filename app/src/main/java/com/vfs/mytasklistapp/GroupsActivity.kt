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



class GroupsActivity : AppCompatActivity(), OnGroupClickedListener
{
    override fun onGroupClicked(position: Int)
    {
        val intent = Intent(this, TasksActivity::class.java)

        intent.putExtra("position", position)

        startActivity(intent)
    }

    override fun onGroupLongClicked(position: Int)
    {
        AppData.groups.removeAt(position) // delete it from the source
        groupsRecyclerView.adapter?.notifyDataSetChanged()
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










