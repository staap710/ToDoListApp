package com.vfs.mytasklistapp

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView


class GroupViewHolder (rootLayout: LinearLayout) : RecyclerView.ViewHolder (rootLayout)
{
    val groupNameTextView = rootLayout.findViewById<TextView>(R.id.groupNameTextView_id)
    val groupCountTextView = rootLayout.findViewById<TextView>(R.id.groupCountTextView_id)

    fun bind (group: Group)
    {
        groupNameTextView.text = group.name
        groupCountTextView.text = "Currently ${group.tasks.size} task(s) is/are active"
    }
}


class GroupAdapter (val listener: OnGroupClickedListener) : RecyclerView.Adapter<GroupViewHolder>()
{
    override fun getItemCount(): Int = AppData.groups.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GroupViewHolder
    {
        val rootLinearLayout = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.group_row,
                parent,
                false) as LinearLayout

        return GroupViewHolder (rootLinearLayout)
    }

    // when I am pushing a new group, what data should I put in there
    override fun onBindViewHolder(holder: GroupViewHolder, position: Int)
    {
        val thisGroup = AppData.groups[position]
        holder.bind(thisGroup)

        holder.itemView.setOnClickListener {
            listener.onGroupClicked(position) // sending the message
        }

        holder.itemView.setOnLongClickListener {
            listener.onGroupLongClicked(position) // sending the message
            true
        }


    }
}