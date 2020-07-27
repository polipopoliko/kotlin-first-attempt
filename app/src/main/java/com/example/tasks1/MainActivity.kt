package com.example.tasks1

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class MainActivity : AppCompatActivity() {
    val RC_ADD = 10
    val rvAdapter = RvListAdapter()
    val KEY_NAME = "todo_list"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvList.adapter = rvAdapter
        rvList.layoutManager = LinearLayoutManager(this)

        rvAdapter.todoList.addAll(loadTodo())
        btnAdd.setOnClickListener{
            val intent = Intent(this, SecondActivity::class.java)
            startActivityForResult(intent, RC_ADD)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RC_ADD && resultCode == Activity.RESULT_OK) {
            val newTitle = data?.getStringExtra("todo_title") ?: "Todo title not found"
            val newDesc = data?.getStringExtra("todo_desc") ?: "Todo description not found"
            rvAdapter.todoList.add(Todo(newTitle, newDesc))
            saveTodo(rvAdapter.todoList)
        }else{
            Toast.makeText(this@MainActivity, R.string.failToAddTodo, Toast.LENGTH_SHORT).show()
        }
    }

    inline fun <reified T> Gson.fromJson(json: String) = fromJson<T>(json, object: TypeToken<MutableList<Todo>>() {}.type)

    fun loadTodo() : List<Todo> {
        var jsonString =  getAppPref().getString(KEY_NAME, "") ?: ""
        return Gson()?.fromJson<MutableList<Todo>>(jsonString) ?: mutableListOf<Todo>()
    }

    fun saveTodo(newTodo: MutableList<Todo>) {
        var stringJson = Gson().toJson(newTodo)
        val editor = getAppPref().edit()
        editor.putString(KEY_NAME, stringJson)
        editor.commit()
    }

    fun getAppPref(): SharedPreferences = getSharedPreferences("todo_app", Context.MODE_PRIVATE)
}