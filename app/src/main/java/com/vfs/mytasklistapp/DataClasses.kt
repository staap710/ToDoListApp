package com.vfs.mytasklistapp

data class Task(var name: String, var completed: Boolean = false)

data class Group (val name: String,
                  var tasks: MutableList<Task> = mutableListOf())
