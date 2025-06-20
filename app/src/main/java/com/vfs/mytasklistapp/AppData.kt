package com.vfs.mytasklistapp


class AppData
{
    companion object
    {
        var groups = mutableListOf<Group>()

        fun initializeGroups ()
        {
            val task1 = Task ("Click to toggle status")
            val task2 = Task ("Long Click to Delete/Edit")
            val task3 = Task ("Click to toggle status", true)
            val task4 = Task ("Long Click to delete")

            val group1 = Group ("Click to view Tasks", mutableListOf(task1, task2))
            val group2 = Group ("Long Click to Delete/Edit", mutableListOf(task3, task4))

            groups = mutableListOf(group1, group2)
        }
    }
}

