package com.example.tasks1

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.second.*

class SecondActivity : Activity (){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.second)

        btnSubmit.setOnClickListener {
            if (etDesc.text.isNotBlank() && etTitle.text.isNotBlank()) {
                val intent = Intent(this, MainActivity::class.java)
                intent.putExtra("todo_title", etTitle.text.toString())
                intent.putExtra("todo_desc", etDesc.text.toString())
                setResult(RESULT_OK, intent)
                finish()
            } else {
                Toast.makeText(this@SecondActivity, "Can not leave empty field", Toast.LENGTH_SHORT).show()
            }
        }
    }
}